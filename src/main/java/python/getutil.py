# -*- coding: utf-8 -*-
"""getutil.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1k5ust5aFeYJby5TvUDyuQGfoiOTfp_wT

Este notebook contém funções que retornam diferentes informações sobre vértices e arestas (ou arcos) de um grafo. Inclui também funções que calculam propriedades como partições em um grafo bipartido e a distância entre 2 vértices.

O código pode ser importado em seu notebook usando o seguinte comando:

!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/getutil.py

Testes para as funções encontram-se neste notebook:

https://colab.research.google.com/drive/1Prk2E2a69kyqcmVGQyz_vmqzMsD62Dsp?
"""

# Se desejar compilar, descomente o comando abaixo
#!pip install jgrapht

# Importando funções
from jgrapht.algorithms.shortestpaths import yen_k_loopless
from jgrapht.properties import is_bipartite

"""## get_vertice_labels, get_edge_labels

Função que retorna uma lista com os labels dos vértices de um grafo ou um subconjunto deles.
Parâmetros:
- g - instância do grafo 
- v_attrs - dicionário de atributos dos vértices
- label - nome do campo no dicionário que contém o label a ser considerado
- subset - sub-conjunto de vértices a serem retornados (default=None, indica todos os vértices)
"""

def get_vertice_labels (g,v_attrs,label='label',subset=None):
  if not subset is None:
    vlist = subset
  else:
    vlist = g.vertices
  try:
    return [v_attrs[v][label] for v in vlist]
  except:
    return vlist

"""Função que retorna uma lista de arestas, onde os vértices podem ser representados por labels (ao invés de seus ids), com ou sem label, com ou sem peso.
Parâmetros:
- g - instância do grafo
- v_attrs - dicionário de atributos dos vértices (default {}, os vértices serão representados por seus ids)
- e_attrs - dicionário de atributos de arestas (default {}, as arestas não terão label)
- vlabel - nome do campo no dicionário de vértices que contém o label a ser considerado
- elabel - nome do campo no dicionário de arestas que contém o label a ser considerado
- subset - subconjunto de arestas a serem retornados (default None, indica todas as arestas)
- weight - indica se pesos devem ser retornados (default False)

"""

def get_edge_labels (g,v_attrs={},e_attrs={},vlabel='label',elabel='label',
                     subset=None,weight=False):
  result = []
  list_edges = g.edges
  if not subset is None:
    list_edges = subset
  for e in list_edges:
    source = g.edge_source(e)
    target = g.edge_target(e)
    if v_attrs != {} and vlabel in v_attrs[source].keys():
      source = v_attrs[source][vlabel]
      target = v_attrs[target][vlabel]
    e_tuple = (source,target)
    if e_attrs != {} and elabel in e_attrs[e].keys():
      e_tuple = (e_attrs[e][elabel],source,target)
    if weight:
      e_tuple = e_tuple + tuple([g.get_edge_weight(e)])
    result.append(e_tuple)
  return result

"""## get_vertexid, get_edge_ids_between, get_arc_ids_between

Retorna o identificador de um vértice a partir do valor do atributo 'label' no dicionário de vértices
"""

def get_vertexid (label, attrs):
  for v,att in attrs.items():
    if att['label'] == label:
      return v
  return None

"""Retorna o conjunto de arestas com terminais entre *x* e *y* em grafos não direcionados *g*"""

def get_edge_ids_between (g,x,y):
  if (x in g.vertices) and (y in g.vertices):
    edges = []
    for e in g.edges:
      source = g.edge_source(e)
      target = g.edge_target(e)
      if (source==x and target==y) or (source==y and target==x):
        edges.append(e) 
    return edges
  else:
     return None

"""Retorna o conjunto de arcos com terminais de *x* para *y* em grafos direcionados *d*"""

def get_arc_ids_between (d,x,y):
  if (x in d.vertices) and (y in d.vertices):
    arcs = []
    for a in d.edges:
      source = d.edge_source(a)
      target = d.edge_target(a)
      if (source==x and target==y):
        arcs.append(a) 
    return arcs
  else:
     return None

"""## get_partitions

Função que retorna as partições (X,Y) de um grafo bipartido g.
"""

# Retorna partições de vértices em um grafo bipartido
def get_partitions (g):
  if is_bipartite(g):
    X = []
    Y = []
    for v in g.vertices:
      if (v not in X) and all(not g.contains_edge_between(v,x) for x in X):
        X.append(v)
      else:
        Y.append(v)
    return X,Y

"""## dist

Função que retorna a distância entre 2 vértices de um grafo não-direcionado ou direcionado. Se não existir um caminho (dirigido) entre os vértices, retorna None.
"""

def get_dist(g,v1,v2):
  if v1 in g.vertices and v2 in g.vertices:
    try:
      return len(next(yen_k_loopless(g,v1,v2,1)).edges)
    except StopIteration:
      return None
  else:
    return None

"""# vertex_list e edge_list

Retorna uma lista com os vértices de um grafo. Cada vértice é representado pelo tupla (id, label), caso o vértice possua um label ou simplesmente id.

Parâmetros:
-  g é a instância do grafo
-  v_attrs é um dicionário com os atributos dos vértices, se existirem
-  label é o nome de um atributo dos vértices que pode ser impresso como label; default é 'label'
-  subset é um subconjunto dos vértices; se [], indica todos os vértices.
"""

def vertex_list (g,v_attrs={},label='label',subset=None):
  result = []
  list_vertices = g.vertices
  if not subset is None:
    list_vertices = subset
  for v in list_vertices:
    str_v = v
    if v in v_attrs.keys() and label in v_attrs[v].keys():
      str_v = (v,v_attrs[v][label])
    result.append(str_v)
  return result

"""Retorna uma lista com as arestas de um grafo. Cada aresta é representada por uma tupla (id, source, target, label, weight). Atributo 'label' é default para identificar o label da aresta. A tupla conterá label e weight apenas fornecidos (ver parâmetros abaixo).

Parâmetros:
-  g é a instância do grafo
-  v_attrs é um dicionário com os atributos dos vértices, se existirem
-  e_attrs é um dicionário com os atributos das arestas, se existirem
-  vlabel é o nome de um atributo dos vértices que pode ser impresso como label; default é 'label'
- elabel é o nome de um atributo das arestas que pode ser impresso como label; default é 'label'
-  weight indica se os pesos das arestas devem ser impressos, caso existam
-  subset é um subconjunto de arestas; se None, indica todas as arestas.
"""

def edge_list(g,v_attrs={},e_attrs={},
                vlabel='label',elabel='label',weight=False,subset=None):
  result = []
  list_edges = g.edges
  if not subset is None:
    list_edges = subset
  for e in list_edges:
    source = g.edge_source(e)
    target = g.edge_target(e)
    if v_attrs != {} and vlabel in v_attrs[source].keys():
      source = v_attrs[source][vlabel]
      target = v_attrs[target][vlabel]
    e_tuple = (e,source,target)
    if e_attrs != {} and elabel in e_attrs[e].keys():
      e_tuple = (e,source,target,e_attrs[e][elabel])
    if weight:
      e_tuple = e_tuple + tuple([g.get_edge_weight(e)])
    result.append(e_tuple)
  return result