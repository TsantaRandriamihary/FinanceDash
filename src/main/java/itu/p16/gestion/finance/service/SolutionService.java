package itu.p16.gestion.finance.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import itu.p16.gestion.finance.model.Analyse;
import itu.p16.gestion.finance.model.SolutionResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolutionService {

    private static final String API_KEY = "twOauhorhwfvrb7Z0HqApE8vP4x4aiOramkqLidm";  // Votre clé API
    private static final String API_URL = "https://api.cohere.ai/v1/generate";

    public void generateSolutionsInBulk(List<Analyse> analyses) {
        this.afficherAnalyses(analyses);
        // Construire le prompt pour toutes les analyses
        StringBuilder prompt = new StringBuilder("Contexte : ");
        prompt.append("L'entreprise GTM est une société en croissance qui souhaite optimiser ses performances financières. ");
        prompt.append("Elle cherche des solutions pratiques pour améliorer la rentabilité, la liquidité, et la gestion de ses dettes tout en maintenant sa compétitivité et ses valeurs d'entreprise. ");
        prompt.append("Objectif : ");
        prompt.append("Répondez en français Proposer des solutions concrètes et adaptées pour chaque analyse financière, basées sur les valeurs et interprétations fournies, afin d'optimiser la performance globale de l'entreprise. ");
        prompt.append("Les solutions doivent être simples, efficaces, et formulées en un maximum de trois phrases. ");
        prompt.append("Voici les analyses : [");

        for (int i = 0; i < analyses.size(); i++) {
            Analyse analyse = analyses.get(i);
            String safeNomAnalyse = analyse.getNomAnalyse().replace("\"", "\\\"").replace("\n", "\\n");
            prompt.append(String.format("{\"nomAnalyse\": \"%s\", \"valeur\": %.2f, \"interpretation\": \"%s\"}",
                    safeNomAnalyse, analyse.getValeur(), analyse.getInterpretation()));
            if (i < analyses.size() - 1) {
                prompt.append(", ");
            }
        }
        prompt.append("]. Répondez en français les solutions sous forme de JSON avec le format suivant : [{\"nomAnalyse\": \"Nom\", \"solution\": \"Votre solution ici\"}, ...].");

        // Configurer les en-têtes HTTP avec la clé API
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestBody = Map.of(
                    "prompt", prompt.toString(),
                    "model", "command-xlarge"
            );
            String body = objectMapper.writeValueAsString(requestBody);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            // Appeler l'API Cohere
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            // Analyser la réponse
            JsonNode root = objectMapper.readTree(response.getBody());
            String text = root.path("generations").get(0).path("text").asText();

            // Extraire les solutions par analyse
            Map<String, String> solutions = extractSolutionsByAnalysis(text);
            System.out.println(text);
            System.out.println(solutions);

            // Associer les solutions aux analyses
            for (Analyse analyse : analyses) {
                String solution = solutions.get(analyse.getNomAnalyse());
                if (solution != null) {
                    analyse.setSolution(solution);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'analyse de la réponse de l'API : " + e.getMessage());
        }
    }

    public static Map<String, String> extractSolutionsByAnalysis(String rawText) {
        Map<String, String> solutions = new HashMap<>();

        try {
            // Isoler la partie JSON
            int jsonStart = rawText.indexOf("[");
            int jsonEnd = rawText.lastIndexOf("]");
            if (jsonStart == -1 || jsonEnd == -1) {
                throw new IllegalArgumentException("JSON introuvable dans le texte fourni.");
            }

            String jsonText = rawText.substring(jsonStart, jsonEnd + 1);

            // Parse le JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonText);

            // Extraire les données
            for (JsonNode node : root) {
                String nomAnalyse;
                if (node.has("nomAnalyse") && !node.path("nomAnalyse").isMissingNode()) {
                    nomAnalyse = node.path("nomAnalyse").asText();
                    String solution = node.path("solution").asText();
                    solutions.put(nomAnalyse, solution);
                } else if (node.has("nomAnalyze") && !node.path("nomAnalyze").isMissingNode()) {
                    nomAnalyse = node.path("nomAnalyze").asText();
                    String solution = node.path("solution").asText();
                    solutions.put(nomAnalyse, solution);
                } else {
                    nomAnalyse = null;
                }
                
            }
            
        } catch (Exception e) {
            System.err.println("Erreur lors de l'extraction des solutions : " + e.getMessage());
        }

        return solutions;
    }

    public String generateGlobalConclusion(List<Analyse> analyses) {
        StringBuilder summary = new StringBuilder();
    
        // Ajouter chaque analyse avec ses interprétations et solutions
        for (Analyse analyse : analyses) {
            summary.append(String.format("Analyse : %s, Valeur : %.2f, Interprétation : %s, Solution : %s. ",
                    analyse.getNomAnalyse(), analyse.getValeur(), analyse.getInterpretation(), analyse.getSolution()));
        }
    
        // Construire le prompt pour la conclusion globale avec limitation à 5 phrases
        String prompt = String.format(
                "Contexte : Voici un résumé des analyses financières de l'entreprise GTM : %s Objectif : Répondez en français Résumer l'état global financier de l'entreprise en une phrase unique et proposer une solution globale adaptée pour améliorer cette situation, toujours en une phrase unique. Le tout doit tenir en maximum 5 phrases. Répondez en français.",
                summary.toString());
    
        // Configurer les en-têtes HTTP avec la clé API
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);
    
        // Construire le corps de la requête JSON
        String body = String.format("{\"prompt\": \"%s\", \"model\": \"command-xlarge\"}", prompt);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
    
        try {
            // Appeler l'API Cohere
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
    
            // Désérialiser la réponse JSON pour extraire le champ 'text'
            ObjectMapper objectMapper = new ObjectMapper();
            // Analyser la réponse
            JsonNode root = objectMapper.readTree(response.getBody());
            String text = root.path("generations").get(0).path("text").asText();

            if (!text.isEmpty()) {
                return text;
            } else {
                return "Aucune conclusion générée.";
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'analyse de la réponse de l'API : " + e.getMessage());
        }
    }

    public void afficherAnalyses(List<Analyse> analyses) {
        System.out.println("Liste des analyses financières :");
        for (Analyse analyse : analyses) {
            System.out.println("Analyse : " + analyse.getNomAnalyse());
            System.out.println("Valeur : " + analyse.getValeur());
            System.out.println("Interprétation : " + analyse.getInterpretation());
            System.out.println("Solution : " + analyse.getSolution());
            System.out.println("-------------------------------------");
        }
    }
}

