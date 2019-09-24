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
		label	"f1"
		graphics
		[
			x	332.0
			y	165.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"f1"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"f2"
		graphics
		[
			x	518.0
			y	111.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"f2"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"f3"
		graphics
		[
			x	682.0
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
			text	"f3"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	3
		label	"f4"
		graphics
		[
			x	605.0
			y	389.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"f4"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	4
		label	"f5"
		graphics
		[
			x	579.0
			y	28.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"f5"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	5
		label	"f6"
		graphics
		[
			x	181.0
			y	300.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"f6"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	edge
	[
		source	0
		target	1
		label	"j*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	332.0
					y	165.0
				]
				point
				[
					x	451.0
					y	167.0
				]
				point
				[
					x	503.0
					y	110.0
				]
				point
				[
					x	518.0
					y	111.0
				]
			]
		]
		LabelGraphics
		[
			text	"j*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	11.3359375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	0
		target	1
		label	"k*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	332.0
					y	165.0
				]
				point
				[
					x	407.0
					y	105.0
				]
				point
				[
					x	518.0
					y	111.0
				]
			]
		]
		LabelGraphics
		[
			text	"k*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	14.669921875
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	1
		target	2
		label	"i*"
		graphics
		[
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"i*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	11.3359375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	2
		target	0
		label	"m*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	682.0
					y	219.0
				]
				point
				[
					x	509.0
					y	218.0
				]
				point
				[
					x	332.0
					y	165.0
				]
			]
		]
		LabelGraphics
		[
			text	"m*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	18.666015625
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	2
		target	3
		label	"h*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	682.0
					y	219.0
				]
				point
				[
					x	684.0
					y	314.0
				]
				point
				[
					x	605.0
					y	389.0
				]
			]
		]
		LabelGraphics
		[
			text	"h*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	15.34375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	2
		target	4
		label	"g*"
		graphics
		[
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"g*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	15.34375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	3
		target	0
		label	"f*"
		graphics
		[
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"f*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	12.00390625
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	3
		target	5
		label	"e*"
		graphics
		[
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"e*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	15.34375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	0
		target	0
		label	"a*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	332.0
					y	165.0
				]
				point
				[
					x	278.0
					y	92.0
				]
				point
				[
					x	252.0
					y	117.0
				]
				point
				[
					x	320.0
					y	174.0
				]
				point
				[
					x	332.0
					y	165.0
				]
			]
		]
		LabelGraphics
		[
			text	"a*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	15.34375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	4
		target	0
		label	"c*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	579.0
					y	28.0
				]
				point
				[
					x	391.0
					y	45.66666666666667
				]
				point
				[
					x	332.0
					y	165.0
				]
			]
		]
		LabelGraphics
		[
			text	"c*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	14.669921875
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	4
		target	5
		label	"d*"
		graphics
		[
			fill	"#000000"
			Line
			[
				point
				[
					x	579.0
					y	28.0
				]
				point
				[
					x	195.0
					y	23.666666666666657
				]
				point
				[
					x	181.0
					y	300.0
				]
			]
		]
		LabelGraphics
		[
			text	"d*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	15.34375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	5
		target	0
		label	"b*"
		graphics
		[
			fill	"#000000"
		]
		LabelGraphics
		[
			text	"b*"
			fontSize	12
			fontName	"Dialog"
			configuration	"AutoFlippingLabel"
			contentWidth	15.34375
			contentHeight	18.701171875
			model	"null"
			position	"null"
		]
	]
]
