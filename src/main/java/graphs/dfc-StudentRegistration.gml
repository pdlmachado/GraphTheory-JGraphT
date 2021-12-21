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
		label	"Submit
Registration"
		graphics
		[
			x	71.5
			y	109.0
			w	118.0
			h	58.0
			customconfiguration	"com.yworks.flowchart.process"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Submit
Registration"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	1
		label	"Application
Complete?"
		graphics
		[
			x	71.5
			y	204.0
			w	118.0
			h	72.0
			customconfiguration	"com.yworks.flowchart.decision"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Application
Complete?"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"Minimum
Standard
Met"
		graphics
		[
			x	71.5
			y	324.701171875
			w	133.0
			h	72.0
			customconfiguration	"com.yworks.flowchart.decision"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Minimum
Standard
Met"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	3
		label	"Write Rejection
Letter"
		graphics
		[
			x	273.2015873015873
			y	443.15234375
			w	128.0
			h	67.0
			customconfiguration	"com.yworks.flowchart.process"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Write Rejection
Letter"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	4
		label	"Letter"
		graphics
		[
			x	71.5
			y	665.603515625
			w	128.0
			h	78.0
			customconfiguration	"com.yworks.flowchart.document"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Letter"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	5
		label	"Suitable for
Program?"
		graphics
		[
			x	71.5
			y	443.15234375
			w	118.0
			h	67.5
			customconfiguration	"com.yworks.flowchart.decision"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Suitable for
Program?"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	6
		label	"Write Acceptance
Letter"
		graphics
		[
			x	71.5
			y	559.103515625
			w	128.0
			h	67.0
			customconfiguration	"com.yworks.flowchart.process"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Write Acceptance
Letter"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	7
		label	"Start"
		graphics
		[
			x	71.5
			y	23.0
			w	118.0
			h	46.0
			customconfiguration	"com.yworks.flowchart.start1"
			fill	"#E8EEF7"
			fill2	"#B7C9E3"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"Start"
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
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	1.0
			yTarget	-1.0
		]
	]
	edge
	[
		source	1
		target	2
		label	"Yes"
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	1.0
			yTarget	-1.0
		]
		LabelGraphics
		[
			text	"Yes"
			fontSize	12
			fontName	"Dialog"
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	2
		target	3
		label	"No"
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	71.5
					y	324.701171875
				]
				point
				[
					x	273.2015873015873
					y	324.701171875
				]
				point
				[
					x	273.2015873015873
					y	443.15234375
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			yTarget	-1.0
		]
		LabelGraphics
		[
			text	"No"
			fontSize	12
			fontName	"Dialog"
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	3
		target	4
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	273.2015873015873
					y	443.15234375
				]
				point
				[
					x	273.2015873015873
					y	665.603515625
				]
				point
				[
					x	71.5
					y	665.603515625
				]
			]
		]
		edgeAnchor
		[
			ySource	1.0
			xTarget	1.0
		]
	]
	edge
	[
		source	1
		target	0
		label	"No"
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	71.5
					y	204.0
				]
				point
				[
					x	173.5
					y	204.0
				]
				point
				[
					x	173.5
					y	109.0
				]
				point
				[
					x	71.5
					y	109.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	1.0
		]
		LabelGraphics
		[
			text	"No"
			fontSize	12
			fontName	"Dialog"
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	2
		target	5
		label	"Yes"
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	1.0
			yTarget	-1.0
		]
		LabelGraphics
		[
			text	"Yes"
			fontSize	12
			fontName	"Dialog"
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	5
		target	3
		label	"No"
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	-1.0
		]
		LabelGraphics
		[
			text	"No"
			fontSize	12
			fontName	"Dialog"
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	5
		target	6
		label	"Yes"
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	1.0
			yTarget	-1.0
		]
		LabelGraphics
		[
			text	"Yes"
			fontSize	12
			fontName	"Dialog"
			model	"null"
			position	"null"
		]
	]
	edge
	[
		source	6
		target	4
		label	""
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	1.0
			yTarget	-1.0
		]
		LabelGraphics
		[
		]
	]
	edge
	[
		source	7
		target	0
		label	""
		graphics
		[
			smoothBends	1
			fill	"#000000"
			targetArrow	"standard"
		]
		edgeAnchor
		[
			ySource	1.0
			yTarget	-1.0
		]
		LabelGraphics
		[
		]
	]
]
