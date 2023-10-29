$(function () {
    "use strict";


	
	// Extra chart
	 Morris.Area({
		element: 'extra-area-chart',
		data: [{
					period: '2010',
					iphone: 0,
					ipad: 0,
					itouch: 0
				}, {
					period: '2011',
					iphone: 50,
					ipad: 15,
					itouch: 5
				}, {
					period: '2012',
					iphone: 20,
					ipad: 50,
					itouch: 65
				}, {
					period: '2013',
					iphone: 60,
					ipad: 12,
					itouch: 7
				}, {
					period: '2014',
					iphone: 30,
					ipad: 20,
					itouch: 120
				}, {
					period: '2015',
					iphone: 25,
					ipad: 80,
					itouch: 40
				}, {
					period: '2016',
					iphone: 10,
					ipad: 10,
					itouch: 10
				}


				],
				lineColors: ['#1dc130', '#2f3d4a', '#009efb'],
				xkey: 'period',
				ykeys: ['iphone', 'ipad', 'itouch'],
				labels: ['Website A', 'Website B', 'Website C'],
				pointSize: 0,
				lineWidth: 0,
				resize:true,
				fillOpacity: 0.8,
				behaveLikeLine: true,
				gridLineColor: '#e0e0e0',
				hideHover: 'auto'
			
		});
}); 