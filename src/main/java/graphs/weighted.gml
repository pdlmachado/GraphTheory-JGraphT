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
		label	"a"
		graphics
		[
			x	466.0
			y	286.0
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
			x	693.0
			y	219.0
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
		label	"d"
		graphics
		[
			x	676.0
			y	443.0
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
		id	3
		label	"c"
		graphics
		[
			x	621.0
			y	320.0
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
		source	2
		target	1
		label	""
		graphics
		[
			fill	"#000000"
		]
		LabelGraphics
		[
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
	]
	edge
	[
		source	0
		target	3
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
]
