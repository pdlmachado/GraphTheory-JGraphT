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
		label	"1"
		graphics
		[
			x	20.0
			y	-50.0
			w	30.0
			h	30.0
			type	"ellipse"
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
		label	"2"
		graphics
		[
			x	70.0
			y	-50.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"2"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	2
		label	"3"
		graphics
		[
			x	380.0
			y	-80.0
			w	30.0
			h	30.0
			type	"ellipse"
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
		id	3
		label	"4"
		graphics
		[
			x	430.0
			y	-80.0
			w	30.0
			h	30.0
			type	"ellipse"
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
		id	4
		label	"5"
		graphics
		[
			x	650.0
			y	-80.0
			w	30.0
			h	30.0
			type	"ellipse"
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
		id	5
		label	"6"
		graphics
		[
			x	700.0
			y	-80.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"6"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	node
	[
		id	6
		label	"7"
		graphics
		[
			x	830.0
			y	-120.0
			w	30.0
			h	30.0
			type	"ellipse"
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
		id	7
		label	"8"
		graphics
		[
			x	200.0
			y	-40.0
			w	30.0
			h	30.0
			type	"ellipse"
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
		id	8
		label	"9"
		graphics
		[
			x	250.0
			y	-40.0
			w	30.0
			h	30.0
			type	"ellipse"
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
	node
	[
		id	9
		label	"10"
		graphics
		[
			x	650.0
			y	-130.0
			w	30.0
			h	30.0
			type	"ellipse"
			raisedBorder	0
			fill	"#FFCC00"
			outline	"#000000"
		]
		LabelGraphics
		[
			text	"10"
			fontSize	12
			fontName	"Dialog"
			model	"null"
		]
	]
	edge
	[
		source	0
		target	1
		label	"[c] There is active network connection and user is logged in"
		graphics
		[
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
			text	"[c] There is active network connection and user is logged in"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	1
		target	2
		label	"[s] emailUser clicks the compose button"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	70.0
					y	-50.0
				]
				point
				[
					x	130.0
					y	-57.5
				]
				point
				[
					x	162.5
					y	-90.0
				]
				point
				[
					x	280.0
					y	-90.0
				]
				point
				[
					x	282.5
					y	-87.5
				]
				point
				[
					x	380.0
					y	-80.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.8645833333333334
			ySource	-0.5
			xTarget	-0.8645833333333334
			yTarget	-0.5
		]
		LabelGraphics
		[
			text	"[s] emailUser clicks the compose button"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	2
		target	3
		label	"[e] system presents a form with recipient, subject and text field and send and discard button"
		graphics
		[
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
			text	"[e] system presents a form with recipient, subject and text field and send and discard button"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	3
		target	4
		label	"[s] emailUser fills out the fields and click on the send button"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	430.0
					y	-80.0
				]
				point
				[
					x	610.0
					y	-68.0
				]
				point
				[
					x	610.75
					y	-68.75
				]
				point
				[
					x	650.0
					y	-80.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.5979166666666667
			ySource	0.8
			xTarget	-0.6604166666666667
			yTarget	0.75
		]
		LabelGraphics
		[
			text	"[s] emailUser fills out the fields and click on the send button"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	4
		target	5
		label	"[e] system informs that message was sent successfully"
		graphics
		[
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
			text	"[e] system informs that message was sent successfully"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	5
		target	6
		label	"[c] message is not held in the draft folder"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	700.0
					y	-80.0
				]
				point
				[
					x	760.0
					y	-80.0
				]
				point
				[
					x	792.5
					y	-112.5
				]
				point
				[
					x	830.0
					y	-120.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	-0.8645833333333334
			yTarget	0.5
		]
		LabelGraphics
		[
			text	"[c] message is not held in the draft folder"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	1
		target	7
		label	"[s] emailUser click on draft button"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	70.0
					y	-50.0
				]
				point
				[
					x	120.0
					y	-42.5
				]
				point
				[
					x	122.5
					y	-40.0
				]
				point
				[
					x	200.0
					y	-40.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.8645833333333334
			ySource	0.5
			xTarget	-1.0
		]
		LabelGraphics
		[
			text	"[s] emailUser click on draft button"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	7
		target	8
		label	"[e] system presents a list of auto saved messages"
		graphics
		[
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
			text	"[e] system presents a list of auto saved messages"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	8
		target	2
		label	"[s] emailUser selects one message"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	250.0
					y	-40.0
				]
				point
				[
					x	310.0
					y	-40.0
				]
				point
				[
					x	342.5
					y	-72.5
				]
				point
				[
					x	380.0
					y	-80.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	-0.8645833333333334
			yTarget	0.5
		]
		LabelGraphics
		[
			text	"[s] emailUser selects one message"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	3
		target	9
		label	"[s] emailUser selects cancel button"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	430.0
					y	-80.0
				]
				point
				[
					x	490.0
					y	-92.0
				]
				point
				[
					x	528.0
					y	-130.0
				]
				point
				[
					x	650.0
					y	-130.0
				]
			]
		]
		edgeAnchor
		[
			xSource	0.5979166666666667
			ySource	-0.8
			xTarget	-1.0
		]
		LabelGraphics
		[
			text	"[s] emailUser selects cancel button"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	9
		target	6
		label	"[e] system closes the email sending form"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	650.0
					y	-130.0
				]
				point
				[
					x	730.0
					y	-130.0
				]
				point
				[
					x	732.5
					y	-127.5
				]
				point
				[
					x	830.0
					y	-120.0
				]
			]
		]
		edgeAnchor
		[
			xSource	1.0
			xTarget	-0.8645833333333334
			yTarget	-0.5
		]
		LabelGraphics
		[
			text	"[e] system closes the email sending form"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	4
		target	3
		label	"[e] system alerts that user does not exist"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	650.0
					y	-80.0
				]
				point
				[
					x	582.25
					y	-76.25
				]
				point
				[
					x	580.0
					y	-74.0
				]
				point
				[
					x	430.0
					y	-80.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-0.96875
			ySource	0.25
			xTarget	0.9145833333333333
			yTarget	0.4
		]
		LabelGraphics
		[
			text	"[e] system alerts that user does not exist"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	4
		target	3
		label	"[e] system alerts that recipient field is empty"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	650.0
					y	-80.0
				]
				point
				[
					x	553.75
					y	-83.75
				]
				point
				[
					x	550.0
					y	-80.0
				]
				point
				[
					x	430.0
					y	-80.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-0.96875
			ySource	-0.25
			xTarget	1.0
		]
		LabelGraphics
		[
			text	"[e] system alerts that recipient field is empty"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
	edge
	[
		source	4
		target	3
		label	"[e] system alerts that message is empty"
		graphics
		[
			fill	"#000000"
			targetArrow	"standard"
			Line
			[
				point
				[
					x	650.0
					y	-80.0
				]
				point
				[
					x	525.25
					y	-91.25
				]
				point
				[
					x	520.0
					y	-86.0
				]
				point
				[
					x	430.0
					y	-80.0
				]
			]
		]
		edgeAnchor
		[
			xSource	-0.6604166666666667
			ySource	-0.75
			xTarget	0.9145833333333333
			yTarget	-0.4
		]
		LabelGraphics
		[
			text	"[e] system alerts that message is empty"
			fontSize	12
			fontName	"Dialog"
			model	"six_pos"
			position	"tail"
		]
	]
]
