Creator	"yFiles"
Version	"2.16"
graph
[
	hierarchic	1
	label	""
	directed	0
	node
	[
		id	0
		label	"a"
		graphics
		[
			x	169.0
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
			text	"a"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"b"
		graphics
		[
			x	283.0
			y	104.0
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
		id	2
		label	"c"
		graphics
		[
			x	447.0
			y	132.0
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
		id	3
		label	"e"
		graphics
		[
			x	378.0
			y	250.0
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
		id	4
		label	"d"
		graphics
		[
			x	202.0
			y	250.0
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
	edge
	[
		source	0
		target	1
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	169.0
					y	121.0
				]
				point
				[
					x	268.0
					y	104.0
				]
				point
				[
					x	283.0
					y	104.0
				]
			]
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
		target	4
		graphics
		[
			fill	"#000000"
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
		source	1
		target	3
		graphics
		[
			fill	"#000000"
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
	]
	edge
	[
		source	2
		target	3
		graphics
		[
			fill	"#000000"
		]
	]
	edge
	[
		source	4
		target	3
		graphics
		[
			fill	"#000000"
		]
	]
]
