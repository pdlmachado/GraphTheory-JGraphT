# -*- coding: utf-8 -*-
"""spanningtreeutil.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/10IBcEGowoyhhYwAMzd8Y2wvnWzu7-4Ob

Este notebook contém funções para árvores geradoras.

O código pode ser importado em seu notebook usando o seguinte comando:

!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/spanningtreeutil.py

Testes para as funções encontram-se neste notebook:

https://colab.research.google.com/drive/1SQAV3UYzaoyEbO59MAfgk43Wl1QBtYLN?
"""

# Se desejar compilar, descomente o(s) comando(s) abaixo
#!pip install jgrapht
#!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/connectutil.py
#!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/searchutil.py
#!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/rootedtreeutil.py
#!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/getutil.py

# Importando a JgraphT 
from jgrapht import create_graph
from jgrapht.properties import is_weakly_connected,is_tree
from connectutil import is_bridge

"""## is_spanningTree

Determina se um grafo s é uma árvore geradora de um grafo g

s é uma árvore não-enraizada
Se s é enraizada (dag) passe como jgraptht.views.as_undirected(s)
"""

def is_spanningTree(g,s):
  # Condições para um grafo t ser uma árvore geradora de g
  c1 = lambda t: sorted(g.vertices) == sorted(t.vertices)
  c2 = lambda t: all(e in g.edges for e in t.edges)
  c3 = lambda t: is_tree(t)
  return c1(s) and c2(s) and c3(s)

"""## gen_spanningTree

Constroi uma árvore geradora no parâmetro *s* para um grafo *g*, removendo sucessicas arestas aleatórias de *g* que fazem parte de um ciclo
"""

def gen_spanningTree (g,s):
  if s.number_of_vertices == 0: # s tem que ser um grafo nulo
    if is_weakly_connected(g):
      s.add_vertices_from(g.vertices)
      for e in g.edges:
        s.add_edge(g.edge_source(e),g.edge_target(e),edge=e)
      notbridges = [e for e in s.edges if not is_bridge(e,s)]
      while not notbridges == []:
        i = randint(0,len(notbridges)-1)
        s.remove_edge(notbridges[i])
        notbridges = [e for e in s.edges if not is_bridge(e,s)]
    else:
      return None