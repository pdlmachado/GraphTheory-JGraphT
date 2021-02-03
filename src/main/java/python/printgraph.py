# Imprime os vértices de um grafo
# v_attrs é um dicionário com os atributos dos vértices, se existirem
#   Se v_attrs possui um atributo 'label', o valor associado
#   será impresso para representar o vértice
def print_vertices (g,v_attrs={}):
  list_vertices = list(g.vertices)
  print("Vértices: {", end = "")
  for v in list_vertices:
    str_v = v
    if v in v_attrs.keys() and 'label' in v_attrs[v].keys():
      str_v = v_attrs[v]['label']
    if v != list_vertices[-1]:
      print(str_v, end = ", ")
    else:     
      print(v, end="}\n")

# Imprime as arestas de um grafo
# v_attrs é um dicionário com os atributos dos vértices, se existirem
#   Se v_attrs possui um atributo 'label', o valor associado
#   será impresso para representar o vértice
# e_attrs é um dicionário com os atributos das arestas, se existirem
#   Se e_attrs possui um atributo 'label', o valor associado
#   será impresso para representar a arestas
def print_edges (g,v_attrs={},e_attrs={}):
  list_edges = list(g.edges)
  print("Arestas: {", end = "")
  for e in list_edges:
    source = g.edge_source(e)
    target = g.edge_target(e)
    if v_attrs != {}:
      source = v_attrs[source]['label']
      target = v_attrs[target]['label']
    str_e = "({},{})".format(source,target)
    if e_attrs != {} and 'label' in e_attrs[e].keys():
      str_e = e_attrs[e]['label'] + ":" + str_e
    if e != list_edges[-1]:
      print(str_e,end=", ")
    else:
      print(str_e,end="}\n")