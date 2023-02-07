# -*- coding: utf-8 -*-
"""networkx-util.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1wU_THn-HUQeiGoGNr9JQ_35B3vOi-pLa

Este notebook contém funções auxiliares para uso junto com o pacote NetworkX.

O código pode ser importado em seu notebook usando o seguinte comando:

!wget https://raw.githubusercontent.com/pdlmachado/GraphTheory-JGraphT/master/src/main/java/python/networkx-util.py

Testes para as funções encontram-se neste notebook:

(a definir)

# Draw
"""

# Importando funções
import networkx as nx
import matplotlib.pyplot as plt

def draw_graph (G, pos, node_labels=None, edge_labels=None):
  nx.draw_networkx_nodes(G,pos, node_color="cyan", node_size=500)
  nx.draw_networkx_edges(G,pos)
  if node_labels is None:
    nx.draw_networkx_labels(G,pos)
  else:
    nx.draw_networkx_labels(G,pos,labels=node_labels)
  if edge_labels is None:
    pass
  else:
    nx.draw_networkx_edge_labels(G,pos,edge_labels=edge_labels)
  plt.box(False)
  plt.show()

def draw_multigraph(G,pos):
  nx.draw_networkx_nodes(G,pos, node_color="cyan", node_size=500)
  nx.draw_networkx_labels(G,pos)
  ax = plt.gca()
  for e in G.edges:
    if e[2] > 0:
      ax.annotate("",
                xy=pos[e[0]], xycoords='data',
                xytext=pos[e[1]], textcoords='data',
                arrowprops=dict(arrowstyle="-", color="black",
                                shrinkA=11, shrinkB=11,
                                patchA=None, patchB=None,
                                connectionstyle="arc3,rad=rrr".replace('rrr',str(0.3*e[2])),
                                ),
      )
  nx.draw_networkx_edges(G,pos,arrows=True,edgelist=[e for e in G.edges if e[2]==0])
  plt.axis(False)
  plt.show()

"""# Import"""

"""## read_multiple_csv

Importa um grafo a partir de tabelas com os conjuntos de vértices e arestas.

Parâmetros:
-   G - instância do grafo
-   vfilename - arquivo com tabela de vértices no formato CSV
-   vid - atributo que representa vértices na tabela de arestas
-   efilename - arquivo com tabela de arestas no formato CSV
-   esourceid - atributo que representa o vértice origem
-   etargetid - atributo que representa o vértice destino
-   weightid - atributo que representa o peso das arestas (se existir)
-   delimiter - delimitador utilizado nos arquivos CSV - default: ,
"""
import csv
def read_multiple_CSV(G,
                      vfilename,vid,
                      efilename,esourceid,etargetid,weightid='',
                      delimiter=','):
  # Vertices
  listcsvV = []
  with open(vfilename, newline='') as f:
    reader = csv.reader(f,delimiter=delimiter)    
    for row in reader:
      listcsvV.append(row)
  f.close()
  viddict = {}
  read_vertices(G,listcsvV,vid)
  # Arestas
  listcsvE = []
  with open(efilename, newline='') as f:
    reader = csv.reader(f,delimiter=delimiter)    
    for row in reader:
      listcsvE.append(row)
  f.close()
  read_edges(G,listcsvE,esourceid,etargetid,weightid)

def read_vertices(G,listcsv,vid):  
  headers = listcsv[0]
  vertex_index = headers.index(vid)
  for l in range(1,len(listcsv)):
    node = listcsv[l][vertex_index]
    G.add_node(node)
    for h in range(len(headers)):
      G.nodes[node][headers[h]] = listcsv[l][h]

def read_edges (G,listcsv,esourceid,etargetid,weightid):
  headers = listcsv[0]
  source_index = headers.index(esourceid)
  target_index = headers.index(etargetid)
  if weightid != '':
    weight_index = headers.index(weightid)
  else:
    weight_index = -1
  for l in range(1,len(listcsv)):
    source = listcsv[l][source_index]
    target = listcsv[l][target_index]
    G.add_edge(source,target)
    for h in range(len(headers)):
      G[source][target][headers[h]] = listcsv[l][h]
    if weight_index != -1:
      G[source][target]['weight'] = listcsv[l][weight_index]