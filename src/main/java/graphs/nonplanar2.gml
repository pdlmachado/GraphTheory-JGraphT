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
		label	"1"
		graphics
		[
			x	0.0
			y	125.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"1"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"3"
		graphics
		[
			x	75.0
			y	125.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"3"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"4"
		graphics
		[
			x	225.0
			y	125.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"4"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	3
		label	"5"
		graphics
		[
			x	150.0
			y	125.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"5"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	4
		label	"7"
		graphics
		[
			x	225.0
			y	200.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"7"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	5
		label	"8"
		graphics
		[
			x	75.0
			y	0.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"8"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	6
		label	"9"
		graphics
		[
			x	225.0
			y	50.0
			w	30.0
			h	30.0
			type	"rectangle"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"9"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	edge
	[
		source	5
		target	0
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	75.0
					y	0.0
				]
				point
				[
					x	0.0
					y	0.0
				]
				point
				[
					x	0.0
					y	125.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
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
			Line
			[
				point
				[
					x	225.0
					y	125.0
				]
				point
				[
					x	300.0
					y	125.0
				]
				point
				[
					x	300.0
					y	0.0
				]
				point
				[
					x	75.0
					y	0.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	1.0
		]
	]
	edge
	[
		source	6
		target	2
		graphics
		[
			fill	"#000000"
		]
		edgeAnchor
		[
			ySource	1.0
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
		]
		edgeAnchor
		[
			ySource	-1.0
			yTarget	1.0
		]
	]
	edge
	[
		source	2
		target	3
		graphics
		[
			fill	"#000000"
		]
		edgeAnchor
		[
			xSource	-1.0
			xTarget	1.0
		]
	]
	edge
	[
		source	3
		target	4
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	150.0
					y	125.0
				]
				point
				[
					x	150.0
					y	191.66666666666666
				]
				point
				[
					x	225.0
					y	200.0
				]
			]
		]
		edgeAnchor
		[
			ySource	1.0
			xTarget	-1.0
			yTarget	-0.5555555555555561
		]
	]
	edge
	[
		source	1
		target	5
		graphics
		[
			fill	"#000000"
		]
		edgeAnchor
		[
			ySource	-1.0
			yTarget	1.0
		]
	]
	edge
	[
		source	6
		target	5
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	225.0
					y	50.0
				]
				point
				[
					x	225.0
					y	8.333333333333336
				]
				point
				[
					x	75.0
					y	0.0
				]
			]
		]
		edgeAnchor
		[
			ySource	-1.0
			xTarget	1.0
			yTarget	0.5555555555555557
		]
	]
	edge
	[
		source	6
		target	4
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	225.0
					y	50.0
				]
				point
				[
					x	275.0
					y	50.0
				]
				point
				[
					x	275.0
					y	200.0
				]
				point
				[
					x	225.0
					y	200.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	1.0
		]
	]
	edge
	[
		source	6
		target	3
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	225.0
					y	50.0
				]
				point
				[
					x	150.0
					y	58.33333333333333
				]
				point
				[
					x	150.0
					y	125.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
			ySource	0.5555555555555552
			yTarget	-1.0
		]
	]
	edge
	[
		source	1
		target	0
		graphics
		[
			fill	"#000000"
		]
		edgeAnchor
		[
			xSource	-1.0
			xTarget	1.0
		]
	]
	edge
	[
		source	1
		target	6
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	75.0
					y	125.0
				]
				point
				[
					x	83.33333333333333
					y	50.0
				]
				point
				[
					x	225.0
					y	50.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.5555555555555552
			ySource	-1.0
			xTarget	-1.0
		]
	]
	edge
	[
		source	1
		target	3
		graphics
		[
			fill	"#000000"
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	-1.0
		]
	]
	edge
	[
		source	4
		target	1
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	225.0
					y	200.0
				]
				point
				[
					x	75.0
					y	200.0
				]
				point
				[
					x	75.0
					y	125.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-1.0
			yTarget	1.0
		]
	]
]
