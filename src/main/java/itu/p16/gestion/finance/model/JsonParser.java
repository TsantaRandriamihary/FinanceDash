// package itu.p16.gestion.finance.service;

// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import itu.p16.gestion.finance.model.Analyse;
// import itu.p16.gestion.finance.model.SolutionResponse;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @Service
// public class SolutionService {

//     private static final String API_KEY = "twOauhorhwfvrb7Z0HqApE8vP4x4aiOramkqLidm"; // Votre clé API
//     private static final String API_URL = "https://api.cohere.ai/v1/generate";

//     public void generateSolutionsInBulk(List<Analyse> analyses) {
//         // Construire le prompt pour toutes les analyses
//         StringBuilder prompt = new StringBuilder("Contexte : L'entreprise Ketch'App souhaite optimiser ses performances financières. ");
//         prompt.append("Voici les analyses : [");
        
//         for (int i = 0; i < analyses.size(); i++) {
//             Analyse analyse = analyses.get(i);
//             String safeNomAnalyse = analyse.getNomAnalyse().replace("\"", "\\\"").replace("\n", "\\n");
//             prompt.append(String.format("{\"nomAnalyse\": \"%s\", \"valeur\": %.2f}", safeNomAnalyse, analyse.getValeur()));
//             if (i < analyses.size() - 1) {
//                 prompt.append(", ");
//             }
//         }
//         prompt.append("]. Objectif : Répondez en français Proposer directement des solutions concrètes et logiques pour chaque analyse, en respectant les valeurs de l'entreprise Ketch'App. ");
//         prompt.append("Répondez en français les solutions et sous forme de JSON avec le format suivant : [{\"nomAnalyse\": \"Nom\", \"solution\": \"Votre solution ici\"}, ...].");

//         // Configurer les en-têtes HTTP avec la clé API
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.set("Authorization", "Bearer " + API_KEY);

//         try {
//             ObjectMapper objectMapper = new ObjectMapper();
//             Map<String, Object> requestBody = Map.of(
//                 "prompt", prompt.toString(),
//                 "model", "command-xlarge"
//             );
//             String body = objectMapper.writeValueAsString(requestBody);
//             HttpEntity<String> entity = new HttpEntity<>(body, headers);

//             // Appeler l'API Cohere
//             RestTemplate restTemplate = new RestTemplate();
//             ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
            
//             // Analyser la réponse
//             JsonNode root = objectMapper.readTree(response.getBody());
//             String text = root.path("generations").get(0).path("text").asText();
            
//             // Extraire les solutions par analyse
//             Map<String, String> solutions = extractSolutionsByAnalysis(text);
//             System.out.println(text);
//             System.out.println(solutions);
//             // Associer les solutions aux analyses
//             for (Analyse analyse : analyses) {
//                 System.out.println(analyse.getNomAnalyse());
//                 String solution = solutions.get(analyse.getNomAnalyse());
//                 if (solution != null) {
//                     analyse.setSolution(solution);
//                 }
//             }
            
//         } catch (Exception e) {
//             throw new RuntimeException("Erreur lors de l'analyse de la réponse de l'API : " + e.getMessage());
//         }
//     }

//     public static Map<String, String> extractSolutionsByAnalysis(String rawText) {
//         Map<String, String> solutions = new HashMap<>();
        
//         try {
//             // Isoler la partie JSON
//             int jsonStart = rawText.indexOf("[");
//             int jsonEnd = rawText.lastIndexOf("]");
//             if (jsonStart == -1 || jsonEnd == -1) {
//                 throw new IllegalArgumentException("JSON introuvable dans le texte fourni.");
//             }

//             String jsonText = rawText.substring(jsonStart, jsonEnd + 1);

//             // Parse le JSON
//             ObjectMapper objectMapper = new ObjectMapper();
//             JsonNode root = objectMapper.readTree(jsonText);

//             // Extraire les données
//             for (JsonNode node : root) {
//                 String nomAnalyse = node.path("nomAnalyse").asText();
//                 String solution = node.path("solution").asText();
//                 solutions.put(nomAnalyse, solution);
//             }
//         } catch (Exception e) {
//             System.err.println("Erreur lors de l'extraction des solutions : " + e.getMessage());
//         }

//         return solutions;
//     }
    
    
//     public String generateGlobalConclusion(List<Analyse> analyses) {
//         StringBuilder summary = new StringBuilder();
    
//         // Ajouter chaque analyse avec ses interprétations et solutions
//         for (Analyse analyse : analyses) {
//             summary.append(String.format("Analyse : %s, Valeur : %.2f, Interprétation : %s, Solution : %s. ",
//                     analyse.getNomAnalyse(), analyse.getValeur(), analyse.getInterpretation(), analyse.getSolution()));
//         }
    
//         // Construire le prompt pour la conclusion globale avec limitation à 5 phrases
//         String prompt = String.format(
//                 "Contexte : Voici un résumé des analyses financières de l'entreprise Ketch'App : %s Objectif : Répondez en français Faire une conclusion globale financiere de l'entreprise  en suivant ces etapes : Résumer l'état global financier de l'entreprise en une phrase unique et proposer une solution globale adaptée pour améliorer cette situation, toujours en une phrase unique. Le tout doit tenir en maximum 5 phrases. Répondez en français.",
//                 summary.toString());
    
//         // Configurer les en-têtes HTTP avec la clé API
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.set("Authorization", "Bearer " + API_KEY);
    
//         // Construire le corps de la requête JSON
//         String body = String.format("{\"prompt\": \"%s\", \"model\": \"command-xlarge\"}", prompt);
//         HttpEntity<String> entity = new HttpEntity<>(body, headers);
    
//         try {
//             // Appeler l'API Cohere
//             RestTemplate restTemplate = new RestTemplate();
//             ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
    
//             // Désérialiser la réponse JSON pour extraire le champ 'text'
//             ObjectMapper objectMapper = new ObjectMapper();
//             Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
//             List<Map<String, Object>> generations = (List<Map<String, Object>>) responseMap.get("generations");
    
//             if (generations != null && !generations.isEmpty()) {
//                 return (String) generations.get(0).get("text");
//             } else {
//                 return "Aucune conclusion générée.";
//             }
//         } catch (Exception e) {
//             throw new RuntimeException("Erreur lors de l'analyse de la réponse de l'API : " + e.getMessage());
//         }
//     }
// }
