Creator	"yFiles"
Version	"2.17"
graph
[
	hierarchic	1
	label	""
	directed	1
	node
	[
		id	0
		label	"f"
		graphics
		[
			x	125.0
			y	175.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"f"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"a"
		graphics
		[
			x	175.0
			y	375.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"a"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"b"
		graphics
		[
			x	225.0
			y	225.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"b"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	3
		label	"d"
		graphics
		[
			x	275.0
			y	125.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"d"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	4
		label	"e"
		graphics
		[
			x	275.0
			y	50.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"e"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	5
		label	"c"
		graphics
		[
			x	350.0
			y	275.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"c"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	6
		label	"g"
		graphics
		[
			x	50.0
			y	175.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"g"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	edge
	[
		source	3
		target	4
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	-1.0
			yTarget	1.0
		]
	]
	edge
	[
		source	3
		target	5
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	125.0
				]
				point
				[
					x	350.0
					y	125.0
				]
				point
				[
					x	350.0
					y	275.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			yTarget	-1.0
		]
	]
	edge
	[
		source	3
		target	0
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	125.0
				]
				point
				[
					x	130.0
					y	125.0
				]
				point
				[
					x	125.0
					y	175.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
			xTarget	0.3333333333333333
			yTarget	-1.0
		]
	]
	edge
	[
		source	3
		target	2
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	125.0
				]
				point
				[
					x	275.0
					y	175.0
				]
				point
				[
					x	230.0
					y	175.0
				]
				point
				[
					x	225.0
					y	225.0
				]
			]
		]
		edgeAnchor
		[
			ySource	1.0
			xTarget	0.3333333333333333
			yTarget	-1.0
		]
	]
	edge
	[
		source	4
		target	5
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	50.0
				]
				point
				[
					x	400.0
					y	45.0
				]
				point
				[
					x	400.0
					y	275.0
				]
				point
				[
					x	350.0
					y	275.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			ySource	-0.3333333333333333
			xTarget	1.0
		]
	]
	edge
	[
		source	4
		target	0
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	50.0
				]
				point
				[
					x	120.0
					y	50.0
				]
				point
				[
					x	125.0
					y	175.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
			xTarget	-0.3333333333333333
			yTarget	-1.0
		]
	]
	edge
	[
		source	4
		target	6
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	50.0
				]
				point
				[
					x	275.0
					y	0.0
				]
				point
				[
					x	50.0
					y	0.0
				]
				point
				[
					x	50.0
					y	175.0
				]
			]
		]
		edgeAnchor
		[
			ySource	-1.0
			yTarget	-1.0
		]
	]
	edge
	[
		source	4
		target	2
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	275.0
					y	50.0
				]
				point
				[
					x	325.0
					y	55.0
				]
				point
				[
					x	325.0
					y	215.0
				]
				point
				[
					x	225.0
					y	225.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			ySource	0.3333333333333333
			xTarget	1.0
			yTarget	-0.6666666666666666
		]
	]
	edge
	[
		source	1
		target	5
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	175.0
					y	375.0
				]
				point
				[
					x	300.0
					y	380.0
				]
				point
				[
					x	300.0
					y	350.0
				]
				point
				[
					x	350.0
					y	350.0
				]
				point
				[
					x	350.0
					y	275.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			ySource	0.3333333333333333
			yTarget	1.0
		]
	]
	edge
	[
		source	1
		target	0
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	175.0
					y	375.0
				]
				point
				[
					x	125.0
					y	370.0
				]
				point
				[
					x	125.0
					y	175.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
			ySource	-0.3333333333333333
			yTarget	1.0
		]
	]
	edge
	[
		source	1
		target	2
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	175.0
					y	375.0
				]
				point
				[
					x	180.0
					y	325.0
				]
				point
				[
					x	230.0
					y	325.0
				]
				point
				[
					x	225.0
					y	225.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.3333333333333333
			ySource	-1.0
			xTarget	0.3333333333333333
			yTarget	1.0
		]
	]
	edge
	[
		source	5
		target	2
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	350.0
					y	275.0
				]
				point
				[
					x	300.0
					y	270.0
				]
				point
				[
					x	300.0
					y	225.0
				]
				point
				[
					x	225.0
					y	225.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
			ySource	-0.3333333333333333
			xTarget	1.0
		]
	]
	edge
	[
		source	0
		target	6
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			xSource	-1.0
			xTarget	1.0
		]
	]
	edge
	[
		source	0
		target	2
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	125.0
					y	175.0
				]
				point
				[
					x	220.0
					y	175.0
				]
				point
				[
					x	225.0
					y	225.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	-0.3333333333333333
			yTarget	-1.0
		]
	]
	edge
	[
		source	6
		target	2
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	50.0
					y	175.0
				]
				point
				[
					x	55.0
					y	225.0
				]
				point
				[
					x	225.0
					y	225.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.3333333333333333
			ySource	1.0
			xTarget	-1.0
		]
	]
	edge
	[
		source	2
		target	1
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	225.0
					y	225.0
				]
				point
				[
					x	220.0
					y	300.0
				]
				point
				[
					x	170.0
					y	300.0
				]
				point
				[
					x	175.0
					y	375.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-0.3333333333333333
			ySource	1.0
			xTarget	-0.3333333333333333
			yTarget	-1.0
		]
	]
	edge
	[
		source	2
		target	5
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	225.0
					y	225.0
				]
				point
				[
					x	275.0
					y	235.0
				]
				point
				[
					x	275.0
					y	280.0
				]
				point
				[
					x	350.0
					y	275.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			ySource	0.6666666666666666
			xTarget	-1.0
			yTarget	0.3333333333333333
		]
	]
	edge
	[
		source	5
		target	1
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	350.0
					y	275.0
				]
				point
				[
					x	340.0
					y	325.0
				]
				point
				[
					x	275.0
					y	325.0
				]
				point
				[
					x	275.0
					y	370.0
				]
				point
				[
					x	175.0
					y	375.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-0.6666666666666666
			ySource	1.0
			xTarget	1.0
			yTarget	-0.3333333333333333
		]
	]
	edge
	[
		source	5
		target	6
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	350.0
					y	275.0
				]
				point
				[
					x	360.0
					y	425.0
				]
				point
				[
					x	0.0
					y	425.0
				]
				point
				[
					x	0.0
					y	175.0
				]
				point
				[
					x	50.0
					y	175.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.6666666666666666
			ySource	1.0
			xTarget	-1.0
		]
	]
	edge
	[
		source	6
		target	1
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	50.0
					y	175.0
				]
				point
				[
					x	45.0
					y	380.0
				]
				point
				[
					x	175.0
					y	375.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-0.3333333333333333
			ySource	1.0
			xTarget	-1.0
			yTarget	0.3333333333333333
		]
	]
]
