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
		label	"b"
		graphics
		[
			x	161.0
			y	116.0
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
			anchor	"c"
		]
	]
	node
	[
		id	1
		label	"c"
		graphics
		[
			x	260.0
			y	116.0
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
			anchor	"c"
		]
	]
	node
	[
		id	2
		label	"d"
		graphics
		[
			x	212.0
			y	189.0
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
			anchor	"c"
		]
	]
	node
	[
		id	3
		label	"a"
		graphics
		[
			x	212.0
			y	39.0
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
			anchor	"c"
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
		source	2
		target	0
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
			fill	"#000000"
		]
	]
	edge
	[
		source	3
		target	0
		graphics
		[
			fill	"#000000"
		]
	]
	edge
	[
		source	3
		target	1
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
]
