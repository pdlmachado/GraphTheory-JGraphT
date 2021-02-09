# Retorna uma lista com os vértices de um grafo.
# Cada vértice é representado pelo tupla (id, label), caso o vértice possua
# um label ou simplesmente id.
# Parâmetros:
#  g é a instância do grafo
#  v_attrs é um dicionário com os atributos dos vértices, se existirem
#  label é o nome de um atributo dos vértices que pode ser impresso como label;
#       default é 'label'
#  subset é um subconjunto dos vértices; se [], indica todos os vértices.
def vertex_list (g,v_attrs={},label='label',subset=[]):
  result = []
  list_vertices = g.vertices
  if (subset != []):
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
                vlabel='label',elabel='label',weight=False,subset=[]):
  result = []
  list_edges = g.edges
  if subset != []:
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

# Importa um grafo a partir de tabelas com os conjuntos de vértices e arestas
# Parâmetros:
#   csvgraph - instância do grafo
#   v_attrs - instância do dicionário de atributos de vértices
#   e_attrs - instância do dicionário de atributos de arestas
#   vfilename - arquivo com tabela de vértices no formato CSV
#   vid - atributo que representa vértices na tabela de arestas
#   efilename - arquivo com tabela de arestas no formato CSV
#   esourceid - atributo que representa o vértice origem
#   etargetid - atributo que representa o vértice destino
#   weightid - atributo que representa o peso das arestas (se existir)
#   delimiter - delimitador utilizado nos arquivos CSV - default: ,
import csv
def read_multiple_CSV(csvgraph,v_attrs,e_attrs,
                      vfilename,vid,
                      efilename,esourceid,etargetid,weightid='',
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
  viddict = {}
  for i in range(1,len(listcsv)):
    csvgraph.add_vertex(i)
    viddict[listcsv[i][vertex_index]] = i
    dict = {}
    for j in range(len(headers)):
        dict[headers[j]] = listcsv[i][j]
    dict['label'] = listcsv[i][vertex_index]
    v_attrs[i] = dict
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
    e_attrs[e] = dict

##########################################
# Importa grafo no formato GML
from jgrapht.io.importers import parse_gml
def import_gml (g,v_attrs,e_attrs,filename):

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

## Desenha um grafo 
# Para executar esta função é necessário importar as bibliotecas: 
import jgrapht.drawing.draw_matplotlib as draw_matplotlib
import matplotlib.pyplot as plt
# Parâmetros:
#   graph - instância do grafo
#   layout - circular|random|fruchterman_reingold|fruchterman_reingold_indexed
#   vlabel, v_attrs - identificador do label de vértices e dicionário
#   elabel, e_attrs - identificador do label de arestas e dicionário
#   demais: cores
#   axis - desenha ou não um frame
def draw_simple(graph,layout='circular',
                vertexid_aslabel=False,
                vlabel='',v_attrs={},elabel='',e_attrs={},
                vertex_color='blue',
                edge_color='black',
                vertex_font_color='white',
                edge_font_color='gray',
                axis=True
                ):
  
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
)
plt.show()

# Retorna o identificador de um vértice a partir do valor do atributo 'label'
def get_vertexid (label, attrs):
  for v,att in attrs:
    if att['label'] == label:
      return v
  return None
