Creator	"yFiles"
Version	"2.14"
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
			x	100.0
			y	101.0
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
	node
	[
		id	1
		label	"b"
		graphics
		[
			x	283.0
			y	103.0
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
		id	2
		label	"c"
		graphics
		[
			x	280.0
			y	231.0
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
		id	3
		label	"d"
		graphics
		[
			x	98.0
			y	230.0
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
		id	4
		label	"e"
		graphics
		[
			x	27.0
			y	229.0
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
			anchor	"c"
		]
	]
	edge
	[
		source	0
		target	1
		label	"s"
		graphics
		[
			width	2
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"s"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	10.0
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	1
		target	2
		label	"u"
		graphics
		[
			width	2
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"u"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	10.673828125
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	2
		target	3
		label	"w"
		graphics
		[
			width	2
			fill	"#000000"
			Line
			[
				point
				[
					x	280.0
					y	231.0
				]
				point
				[
					x	203.0
					y	212.0
				]
				point
				[
					x	98.0
					y	230.0
				]
			]
		]
		LabelGraphics
		[
			text	"w"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	12.666015625
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	3
		target	2
		label	"v"
		graphics
		[
			width	2
			fill	"#000000"
			Line
			[
				point
				[
					x	98.0
					y	230.0
				]
				point
				[
					x	203.0
					y	266.0
				]
				point
				[
					x	280.0
					y	231.0
				]
			]
		]
		LabelGraphics
		[
			text	"v"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	10.0
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	4
		target	3
		label	"z"
		graphics
		[
			width	2
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"z"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	10.0
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	0
		target	3
		label	"y"
		graphics
		[
			width	2
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"y"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	10.0
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	3
		target	1
		label	"x"
		graphics
		[
			width	2
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"x"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	10.0
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	0
		target	0
		label	"t"
		graphics
		[
			width	2
			fill	"#000000"
			Line
			[
				point
				[
					x	100.0
					y	101.0
				]
				point
				[
					x	100.0
					y	61.0
				]
				point
				[
					x	100.0
					y	101.0
				]
			]
		]
		LabelGraphics
		[
			text	"t"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	7.333984375
			contentHeight	18.701171875
			model	"six_pos"
			position	"tail"
		]
	]
]
