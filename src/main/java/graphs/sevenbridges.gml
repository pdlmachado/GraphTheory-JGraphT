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
		label	"A"
		graphics
		[
			x	84.0
			y	197.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"A"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"C"
		graphics
		[
			x	163.0
			y	121.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"C"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"B"
		graphics
		[
			x	240.0
			y	197.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"B"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	3
		label	"D"
		graphics
		[
			x	163.0
			y	270.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"D"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	edge
	[
		source	1
		target	2
		graphics
		[
			fill	"#000000"
		]
	]
	edge
	[
		source	3
		target	2
		graphics
		[
			fill	"#000000"
		]
	]
	edge
	[
		source	0
		target	2
		graphics
		[
			fill	"#000000"
		]
	]
	edge
	[
		source	0
		target	1
		graphics
		[
			type	"arc"
			fill	"#000000"
			arcType	"fixedRatio"
			arcHeight	27.405519485473633
			arcRatio	1.0
			Line
			[
				point
				[
					x	84.0
					y	197.0
				]
				point
				[
					x	104.5
					y	139.25
				]
				point
				[
					x	163.0
					y	121.0
				]
			]
		]
	]
	edge
	[
		source	1
		target	0
		graphics
		[
			type	"arc"
			fill	"#000000"
			arcType	"fixedRatio"
			arcHeight	27.405519485473633
			arcRatio	1.0
			Line
			[
				point
				[
					x	163.0
					y	121.0
				]
				point
				[
					x	142.5
					y	178.75
				]
				point
				[
					x	84.0
					y	197.0
				]
			]
		]
	]
	edge
	[
		source	0
		target	3
		graphics
		[
			type	"arc"
			fill	"#000000"
			arcType	"fixedRatio"
			arcHeight	26.89098358154297
			arcRatio	1.0
			Line
			[
				point
				[
					x	84.0
					y	197.0
				]
				point
				[
					x	141.75
					y	213.75
				]
				point
				[
					x	163.0
					y	270.0
				]
			]
		]
	]
	edge
	[
		source	3
		target	0
		graphics
		[
			type	"arc"
			fill	"#000000"
			arcType	"fixedRatio"
			arcHeight	26.89098358154297
			arcRatio	1.0
			Line
			[
				point
				[
					x	163.0
					y	270.0
				]
				point
				[
					x	105.25
					y	253.25
				]
				point
				[
					x	84.0
					y	197.0
				]
			]
		]
	]
]
