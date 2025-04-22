var multipleOptions = {
  series: [73.15, 36.57, 72.66],
  chart: {
  height: 350,
  type: 'radialBar',
},
plotOptions: {
  radialBar: {
    dataLabels: {
      name: {
        fontSize: '22px',
      },
      value: {
        fontSize: '16px',
      },
      total: {
        show: true,
        label: 'Total',
        formatter: function (w) {
          // By default this function returns the average of all series. The below is just an example to show the use of custom formatter function
          return '249%'
        }
      }
    }
  }
},
labels: ['Marge Nette', 'ROA', 'ROE'],
};
var multiSeriesOptions = {
  series: [{
  name: 'Analyse',
  data: [1.8, 0.96],
}],
  chart: {
  height: 'auto',
  type: 'radar'
},
title: {
  text: 'Liquidite (%)'
},
yaxis: {
  stepSize: 20
},
xaxis: {
  categories: ['Liquidite generale', 'Liquidite reduite']
}
};

var simpleDonutOptions = {
  series: [1.8, 0.96],
  chart: {
    type: 'donut',
    height: 'auto' // Hauteur adaptative
  },
  labels: ['Liquidite globale', 'Liquidite reduite'],
  responsive: [{
    breakpoint: 480,
    options: {
      chart: {
        width: 200,
        height: 'auto' // Hauteur spécifique pour petits écrans
      },
      legend: {
        position: 'bottom'
      }
    }
  }]
};

var basicOptions = {
  series: [49.66, 42],
  chart: {
    type: 'polarArea',
    height: 'auto' // Hauteur adaptative
  },
  labels: ['Endetement global', 'Couverture'],
  stroke: {
    colors: ['#fff']
  },
  fill: {
    opacity: 0.8
  },
  responsive: [{
    breakpoint: 480,
    options: {
      chart: {
        width: 200,
        height: 'auto' // Hauteur spécifique pour petits écrans
      },
      legend: {
        position: 'bottom'
      }
    }
  }]
};



var basic = new ApexCharts(document.querySelector("#basic"), basicOptions);
var simpleDonut = new ApexCharts(document.querySelector("#simpleDonut"), simpleDonutOptions);
var multiSeries = new ApexCharts(document.querySelector("#multiSeries"), multiSeriesOptions);
var multiple = new ApexCharts(document.querySelector("#multiple"), multipleOptions);

basic.render();
simpleDonut.render();
multiSeries.render();
multiple.render();
