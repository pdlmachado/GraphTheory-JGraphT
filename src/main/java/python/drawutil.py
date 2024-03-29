# -*- coding: utf-8 -*-
"""drawutil.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1ahniT4MoCORDA48gwnudymIVadV59x1F

Este notebook contém funções para desenhar grafos.

O código pode ser importado em seu notebook usando o seguinte comando:

!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/drawutil.py

Testes para as funções encontram-se neste notebook:

https://colab.research.google.com/drive/12JUu5AjfF65pCNAfa3IrV56OhDWmbcvV?
"""

# Se desejar compilar, descomente o comando abaixo
#!pip install jgrapht

# Importando funções
from jgrapht import create_graph
from jgrapht.drawing import draw_matplotlib
from matplotlib import pyplot
from matplotlib import colors

"""## draw_graph

A função **draw_graph** abaixo desenha um grafo, onde grupos de vértices e arestas podem receber cores e formatos diferentes. 
O único parâmetro obrigatório é o parâmetro g que representa a instância do grafo.

Parâmetros:
* **g** - instância do grafo
* **layout** - circular (default) / random / fruchterman_reingold / fruchterman_reingold_indexed
* **vertexid_aslabel** - ids dos vértices devem ser utilizados como labels
* **edgeid_aslabel** - ids das arestas devem ser utilizados como labels
* **edgeweight_aslabel** - pesos das arestas deve ser usados como labels
* **vlabel**, **v_attrs** - identificador do label de vértices e dicionário
* **elabel**, **e_attrs** - identificador do label de arestas e dicionário
* **vertex_color** - cor do vértice (default: cyan) ou range(n) com índice de variantes em um mapa de cores (parâmetro vmap),
* **vmap** - mapa de cores (defaul None), https://matplotlib.org/stable/gallery/color/colormap_reference.html
* **edge_color** - cor da aresta (default: orange) ou range(n) com índice de variantes em um mapa de cores (parâmetro emap),
* **emap** - mapa de cores (default None), https://matplotlib.org/stable/gallery/color/colormap_reference.html
* **vertex_font_color** - cor da fonte do vértice (default: black) (ver cores disponíveis em https://matplotlib.org/stable/gallery/color/named_colors.html#sphx-glr-gallery-color-named-colors-py)
* **edge_font_color** - cor da fonte da aresta/arco (default: gray) (ver cores disponíveis em https://matplotlib.org/stable/gallery/color/named_colors.html#sphx-glr-gallery-color-named-colors-py)
* **axis** - desenha ou não um frame
* **width**, **height** - largura, altura da janela de exibição
* **vsize** - tamanho do vértice (default: 450) 
* **vshape** - formato do vértice (default: o) (ver outras opções de formato em https://matplotlib.org/stable/api/markers_api.html)

Os seguintes parâmetros devem ser utilizados quando se deseja desenhar grupos de vértices ou arestas/arcos com cores específicas, indicando também uma legenda. Para os vértices e arestas não indicados, serão utilizadas as cores definidas nos parâmetros **vertex_color** e **edge_color**:
* **vset** - lista de listas de vértices determinando os grupos (default: [])
* **vsetcolor** - lista de cores para cada grupo definido em **vset** (default: [])
* **vsetlabel** - lista de legendas para cada grupo definido em **vset** (default: [])
* **eset** - lista de listas de arestas/arcos determinando os grupos (default: [])
* **esetcolor** - lista de cores para cada grupo definido em **eset** (default: [])
* **esetlabel** - lista de legendas para cada grupo definido em **eset** (default: [])
"""

def draw_graph(g,layout="circular",
               vertexid_aslabel=False,edgeid_aslabel=False,edgeweight_aslabel=False,
               vlabel='',v_attrs={},elabel='',e_attrs={},
               vertex_color='cyan',vmap=None,
               edge_color='orange',emap=None,
               vertex_font_color='black',
               edge_font_color='gray',
               axis=False,
               width=8,height=5,vsize=450,vshape='o',
               vset=[],vsetcolor=[],vsetlabel=[],
               eset=[],esetcolor=[],esetlabel=[]):
  flatten = lambda t: [item for sublist in t for item in sublist]
  positions = draw_matplotlib.layout(g, seed=10, name=layout)
  if vset == []:
    draw_matplotlib.draw_jgrapht_vertices(
      g, 
      positions=positions, 
      vertex_list=g.vertices, 
      vertex_color=vertex_color,
      vertex_size=vsize,
      vertex_cmap=vmap,
      vertex_shape=vshape
    )
  else:
    notinvset = [v for v in g.vertices if v not in flatten(vset)]
    for i in range(len(vset)):
      draw_matplotlib.draw_jgrapht_vertices(
        g, 
        positions=positions, 
        vertex_list=vset[i], 
        vertex_color=vsetcolor[i],
        vertex_title=vsetlabel[i],
        vertex_size=vsize,
        vertex_shape=vshape
      )
    if notinvset != []:
      draw_matplotlib.draw_jgrapht_vertices(
        g, 
        positions=positions, 
        vertex_list=notinvset, 
        vertex_color=vertex_color,
        vertex_size=vsize,
        vertex_cmap=vmap,
        vertex_shape=vshape
      )
  if eset == []:
    draw_matplotlib.draw_jgrapht_edges(
      g,
      positions=positions,
      edge_list=g.edges,
      edge_color=edge_color,
      arrow_color=edge_color
    )
  else:
    notineset = [e for e in g.edges if e not in flatten(eset)]
    for i in range(len(eset)):
      draw_matplotlib.draw_jgrapht_edges(
        g,
        positions=positions,
        edge_list=eset[i],
        edge_color=esetcolor[i],
        arrow_color=esetcolor[i],
        edge_title=esetlabel[i]
      )
      if notineset != []:
        draw_matplotlib.draw_jgrapht_edges(
          g,
          positions=positions,
          edge_list=notineset,
          edge_color=edge_color,
          arrow_color=edge_color
        )
  vertex_labels = {}
  if(vlabel!='' and v_attrs!={}):
    for v in g.vertices:
      if vlabel in v_attrs[v].keys():
        vertex_labels[v] = v_attrs[v][vlabel]
  elif vertexid_aslabel:
    for v in g.vertices:
      vertex_labels[v] = str(v)
  edge_labels = {}
  if(elabel!='' and e_attrs!={}):
    for e in g.edges:
      if elabel in e_attrs[e].keys():
        edge_labels[e] = e_attrs[e][elabel]
  elif edgeweight_aslabel:
    for e in g.edges:
      edge_labels[e] = str(g.get_edge_weight(e))
  elif edgeid_aslabel:
    for e in g.edges:
      edge_labels[e] = str(e)
  draw_matplotlib.draw_jgrapht_vertex_labels(
    g,
    positions=positions,
    labels=vertex_labels,
    vertex_font_color=vertex_font_color
  )  
  if (not e_attrs=={}) or (edgeweight_aslabel) or (edgeid_aslabel):
    draw_matplotlib.draw_jgrapht_edge_labels(
      g,
      positions=positions,
      labels=edge_labels,
      edge_font_color=edge_font_color
    )
  pyplot.rcParams['figure.figsize'] = [width,height]
  pyplot.show()