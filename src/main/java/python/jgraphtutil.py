# Importando a JgraphT
import jgrapht

# Importando funções para visualização gráfica
import jgrapht.drawing.draw_matplotlib as draw_matplotlib
import matplotlib.pyplot as plt


from jgrapht.algorithms.shortestpaths import yen_k_loopless
import unittest
from jgrapht.properties import is_bipartite

"""# Listas de Vértices e Arestas"""

# Retorna uma lista com os vértices de um grafo.
# Cada vértice é representado pelo tupla (id, label), caso o vértice possua
# um label ou simplesmente id.
# Parâmetros:
#  g é a instância do grafo
#  v_attrs é um dicionário com os atributos dos vértices, se existirem
#  label é o nome de um atributo dos vértices que pode ser impresso como label;
#       default é 'label'
#  subset é um subconjunto dos vértices; se [], indica todos os vértices.
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

# Retorna uma lista com as arestas de um grafo
# Cada aresta é representada por uma tupla (id, source, target, label, weight)
# Atributo 'label' é default para identificar o label da aresta. 
# A tupla conterá label e weight apenas fornecidos (ver parâmetros abaixo).
# Parâmetros:
#  g é a instância do grafo
#  v_attrs é um dicionário com os atributos dos vértices, se existirem
#  e_attrs é um dicionário com os atributos das arestas, se existirem
#  vlabel é o nome de um atributo dos vértices que pode ser impresso como label;
#     default é 'label'
#  elabel é o nome de um atributo das arestas que pode ser impresso como label;
#     default é 'label'
#  weight indica se os pesos das arestas devem ser impressos, caso existam
#  subset é um subconjunto de arestas; se None, indica todas as arestas.
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
  
# Retorna partições de vértices em um grafo bipartido
def find_partitions (g):
  if is_bipartite(g):
    X = []
    Y = []
    for v in g.vertices:
      if (v not in X) and all(not g.contains_edge_between(v,x) for x in X):
        X.append(v)
      else:
        Y.append(v)
    return X,Y

"""# Importação de Grafos"""

# Importa um grafo a partir de tabelas com os conjuntos de vértices e arestas
# Parâmetros:
#   csvgraph - instância do grafo
#   v_attrs - instância do dicionário de atributos de vértices
#   e_attrs - instância do dicionário de atributos de arestas
#   vfilename - arquivo com tabela de vértices no formato CSV
#   vid - atributo que representa vértices na tabela de arestas
#   vlabel - atributo que representa os labels dos vértices (se existir)
#   efilename - arquivo com tabela de arestas no formato CSV
#   esourceid - atributo que representa o vértice origem
#   etargetid - atributo que representa o vértice destino
#   elabel - atributo que representa os labels das arestas (se existir)
#   weightid - atributo que representa o peso das arestas (se existir)
#   delimiter - delimitador utilizado nos arquivos CSV - default: ,
import csv
def read_multiple_CSV(csvgraph,v_attrs,e_attrs,
                       vfilename,vid,vlabel,
                       efilename,esourceid,etargetid,elabel='',weightid='',
                       delimiter=','):
  # Vertices
  listcsv = []
  with open(vfilename, newline='') as f:
    reader = csv.reader(f,delimiter=delimiter)    
    for row in reader:
      listcsv.append(row)
  f.close()
  headers = listcsv[0]
  vertex_index = headers.index(vid)
  vertexlabel_index = vertex_index
  if vlabel != '':
    vertexlabel_index = headers.index(vlabel)
  viddict = {}
  for i in range(1,len(listcsv)):
    id = i
    try:
      id = int(listcsv[i][vertex_index])
    except:
      pass
    csvgraph.add_vertex(id)
    viddict[listcsv[i][vertex_index]] = id
    dict = {}
    for j in range(len(headers)):
        dict[headers[j]] = listcsv[i][j]
    if vlabel != '':
      dict['label'] = listcsv[i][vertexlabel_index]
    v_attrs[id] = dict
  # Arestas
  listcsv = []
  with open(efilename, newline='') as f:
    reader = csv.reader(f,delimiter=delimiter)    
    for row in reader:
      listcsv.append(row)
  f.close()
  headers = listcsv[0]
  source_index = headers.index(esourceid)
  target_index = headers.index(etargetid)
  edgelabel_index = 0
  if (elabel != ''):
    edgelabel_index = headers.index(elabel)
  weight_index = -1
  if (weightid in headers):
    weight_index = headers.index(weightid)
  for i in range(1,len(listcsv)):
    e = csvgraph.add_edge(viddict[listcsv[i][source_index]],
                          viddict[listcsv[i][target_index]])
    if (weight_index != -1):
      csvgraph.set_edge_weight(e,int(listcsv[i][weight_index]))
    dict = {}
    for j in range(len(headers)):
      if j!=source_index and j!=target_index:
        dict[headers[j]] = listcsv[i][j]
    if (elabel != ''):
      dict['label'] = listcsv[i][edgelabel_index]
    e_attrs[e] = dict

##########################################
# Importa grafo no formato GML
from jgrapht.io.importers import parse_gml
def import_gml (g,v_attrs,e_attrs,filename,weights_aslabel=False):

  # Função que adiciona atributos de vértices
  def v_att_cb(vertex, attribute_name, attribute_value):
      if vertex not in v_attrs:
          v_attrs[vertex] = {}
      v_attrs[vertex][attribute_name] = attribute_value

  # Função que adiciona atributos de arestas
  def e_att_cb(edge, attribute_name, attribute_value):
      if edge not in e_attrs:
          e_attrs[edge] = {}
      e_attrs[edge][attribute_name] = attribute_value

  # Leitura do arquivo em um string de entrada
  gmlfile1 = open(filename,"r")
  input_gml1 = "".join(gmlfile1.readlines())
  gmlfile1.close()

  # Importando a definição no formato GML
  parse_gml(g,input_gml1,
            vertex_attribute_cb=v_att_cb,
            edge_attribute_cb=e_att_cb)
  if weights_aslabel and g.type.weighted:
    for e in g.edges:
      w = int(e_attrs[e]['label'])
      g.set_edge_weight(e,w)

#############
# Cria dicionário v_attrs a partir de input_string no formato CSV 
# edgelist ou adjacencylist. Se weighted=True, o último
# elemento de cada linha será o peso da aresta (para o formato edgelist)
def create_vdict (v_attrs, input_string, weighted=False):
  listcsv = input_string.split('\n')
  vlist = []
  count = 0
  for l in listcsv:
    if l!='':
      vertices = l.split(',')
      if weighted:
        vertices.pop(-1)
      for v in vertices:
       if not(v in vlist):
         v_attrs[count] = {}
         v_attrs[count]['label'] = v
         vlist.append(v)
         count += 1

"""# Desenhando Grafo"""

## Desenha um grafo 
# Para executar esta função é necessário importar as bibliotecas: 
import jgrapht.drawing.draw_matplotlib as draw_matplotlib
import matplotlib.pyplot as plt
# Parâmetros:
#   graph - instância do grafo
#   layout - circular|random|fruchterman_reingold|fruchterman_reingold_indexed
#	vertexid_aslabel - indica os ids dos vértices devem ser utilizados como labels
#	edgeweight_aslabel - indica se os pesos das arestas deve ser usados como labels
#   vlabel, v_attrs - identificador do label de vértices e dicionário
#   elabel, e_attrs - identificador do label de arestas e dicionário
#   demais: cores
#   axis - desenha ou não um frame
#   width,height - largura, altura da janela de exibição
def draw_simple(graph,layout='circular',
                vertexid_aslabel=False,
                edgeweight_aslabel=False,
                vlabel='',v_attrs={},elabel='',e_attrs={},
                vertex_color='blue',
                edge_color='black',
                vertex_font_color='white',
                edge_font_color='gray',
                axis=True,
                width=8,
                height=5,
                vsize=450,
                cmap=None):
  
  vertex_labels = {}
  if(vlabel!='' and v_attrs!={}):
    for v in graph.vertices:
      if vlabel in v_attrs[v].keys():
        vertex_labels[v] = v_attrs[v][vlabel]
  elif vertexid_aslabel:
    for v in graph.vertices:
      vertex_labels[v] = str(v)
  edge_labels = {}
  if(elabel!='' and e_attrs!={}):
    for e in graph.edges:
      if elabel in e_attrs[e].keys():
        edge_labels[e] = e_attrs[e][elabel]
  elif edgeweight_aslabel:
    for e in graph.edges:
      edge_labels[e] = str(graph.get_edge_weight(e))
  positions = draw_matplotlib.layout(graph, seed=10, name=layout)
  draw_matplotlib.draw_jgrapht(
    graph,
    positions=positions,
    vertex_color=vertex_color,
    edge_color=edge_color,
    edge_linewidth=1,
    vertex_labels=vertex_labels,
    vertex_font_color=vertex_font_color,
    edge_labels=edge_labels,
    edge_font_color=edge_font_color,
    axis=axis,
    vertex_size=vsize,
    vertex_cmap=cmap
  )
  plt.rcParams['figure.figsize'] = [width,height]
  plt.show()
  
# Desenha grafo bipartido com partições em p1 e p2
def draw_bipartite(g,p1,p2,vlabel='',v_attrs={},elabel='',e_attrs={},vertexid_aslabel=False):
  positions = draw_matplotlib.layout(g, seed=10, name="circular")
  draw_matplotlib.draw_jgrapht_vertices(
    g, 
    positions=positions, 
    vertex_list=p1, 
    vertex_color="red", 
    vertex_title="Partição 1"
  )
  draw_matplotlib.draw_jgrapht_vertices(
    g, 
    positions=positions, 
    vertex_list=p2, 
    vertex_color="blue", 
    vertex_title="Partição 2"
  )
  draw_matplotlib.draw_jgrapht_edges(
    g,
    positions=positions,
    edge_list=g.edges,
    edge_color="orange"
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
  draw_matplotlib.draw_jgrapht_vertex_labels(
    g,
    positions=positions,
    labels=vertex_labels,
    vertex_font_color="white"
  )  
  if not (e_attrs=={}):
    draw_matplotlib.draw_jgrapht_edge_labels(
      g,
      positions=positions,
      labels=edge_labels
    )
  plt.show()


# Desenha grafo destacando um conjunto de arestas
# Desenha grafo destacando um conjunto de arestas
def draw_cut(g,cut=[],cutlabel='',vlabel='',vset=[],vsetlabel='',
             v_attrs={},elabel='',e_attrs={},vertexid_aslabel=False,
             edgeweight_aslabel=False,layout="circular",width=8,height=5,vsize=450,
             cmap=None,vsetcolor="red"):
  if cutlabel == '':
    cutlabel = 'Edge cut'
  positions = draw_matplotlib.layout(g, seed=10, name=layout)
  notcut = [e for e in g.edges if e not in cut]
  notvset = [v for v in g.vertices if v not in vset]
  if not (notvset == []):
    draw_matplotlib.draw_jgrapht_vertices(
      g, 
      positions=positions, 
      vertex_list=notvset, 
      vertex_color="cyan", 
      vertex_size=vsize,
    )
  if vset != []:
    draw_matplotlib.draw_jgrapht_vertices(
      g, 
      positions=positions, 
      vertex_list=vset, 
      vertex_color=vsetcolor,
      vertex_title=vsetlabel,
      vertex_size=vsize,
      vertex_cmap=cmap
    )
  if cut != []:
    draw_matplotlib.draw_jgrapht_edges(
      g,
      positions=positions,
      edge_list=cut,
      edge_color="orange",
      arrow_color="orange",
      edge_title = cutlabel
    )
  draw_matplotlib.draw_jgrapht_edges(
    g,
    positions=positions,
    edge_list=notcut,
    edge_color="black",
    arrow_color="black",
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
  draw_matplotlib.draw_jgrapht_vertex_labels(
    g,
    positions=positions,
    labels=vertex_labels,
    vertex_font_color="black"
  )  
  if (not e_attrs=={}) or (edgeweight_aslabel):
    draw_matplotlib.draw_jgrapht_edge_labels(
      g,
      positions=positions,
      labels=edge_labels
    )
  plt.rcParams['figure.figsize'] = [width,height]
  plt.show()

# Desenha floresta com até 10 componentes
def draw_components(g,clist,clabel="Componente",vlabel='',v_attrs={},elabel='',e_attrs={},vertexid_aslabel=False,
                    layout="circular",width=8,height=5,vsize=450,vshape='o',vfcolor='black'):
  if len(clist) > 10:
    return None
  positions = draw_matplotlib.layout(g, seed=10, name=layout)
  color_names = ["red", "cyan", "green", "lightgreen", "grey", "lightpink", "orange", "yellow", "violet", "lightblue"]
  i = 1
  for c in clist:
    label = clabel + str(i)
    draw_matplotlib.draw_jgrapht_vertices(
      g, 
      positions=positions, 
      vertex_list=c, 
      vertex_color=color_names[i-1], 
      vertex_title=label,
      vertex_size=vsize,
      vertex_shape=vshape
    )
    i = i+1
  draw_matplotlib.draw_jgrapht_edges(
    g,
    positions=positions,
    edge_list=g.edges,
    edge_color="orange"
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
  draw_matplotlib.draw_jgrapht_vertex_labels(
    g,
    positions=positions,
    labels=vertex_labels,
    vertex_font_color=vfcolor
  )  
  if not (e_attrs=={}):
    draw_matplotlib.draw_jgrapht_edge_labels(
      g,
      positions=positions,
      labels=edge_labels
    )
  plt.rcParams['figure.figsize'] = [width,height]
  plt.show()

  
"""# Propriedades de Vértices e Arestas"""

# Retorna o identificador de um vértice a partir do valor do atributo 'label'
def get_vertexid (label, attrs):
  for v,att in attrs.items():
    if att['label'] == label:
      return v
  return None

# Retorna o conjunto de arestas com terminais entre x e y
# para grafos não direcionados
def get_edges (g,x,y):
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
     
# Retorna o conjunto de arcos com terminais de x para y
# para grafos direcionados
def get_arcs (d,x,y):
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

"""# Passeios, Caminhos e Trilhas"""

# distancia entre 2 vértices de um grafo
def dist(g,v1,v2):
  if v1 in g.vertices and v2 in g.vertices:
    return len(next(yen_k_loopless(g,v1,v2,1)).edges)
  else:
    return None
