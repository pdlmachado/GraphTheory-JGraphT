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
		label	"call (toy(x))"
		graphics
		[
			x	815.0
			y	166.0
			w	162.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"call (toy(x))"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"return positivo"
		graphics
		[
			x	734.5
			y	319.0
			w	106.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"return positivo"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"x > 0"
		graphics
		[
			x	815.0
			y	246.0
			w	76.0
			h	30.0
			type	"diamond"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"x > 0"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	3
		label	"x < 0"
		graphics
		[
			x	893.0
			y	319.0
			w	69.0
			h	30.0
			type	"diamond"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"x < 0"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	4
		label	"return negativo"
		graphics
		[
			x	839.0
			y	392.0
			w	96.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"return negativo"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	5
		label	"return zero"
		graphics
		[
			x	953.0
			y	385.0
			w	88.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"return zero"
			fontSize	12
			fontName	"Dialog"
			model	"null"
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
		]
	]
	edge
	[
		source	2
		target	3
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
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
	]
	edge
	[
		source	3
		target	5
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
		]
	]
]
