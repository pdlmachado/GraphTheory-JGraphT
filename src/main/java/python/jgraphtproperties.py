from jgrapht.algorithms.shortestpaths import yen_k_loopless
from jgrapht.views import as_masked_subgraph
##############################################
### Determina se uma aresta é ponte
def is_bridge(e,g):
  
  # Cria uma visão de subgrafo para o grafo g sem a aresta
  vmask = lambda v : False
  emask = lambda x : x == e
  subgraph = as_masked_subgraph(g,vertex_mask_cb=vmask,edge_mask_cb=emask)
  # Verifica se existe um caminho entre os vértices no subgrafo
  paths = yen_k_loopless(subgraph,g.edge_source(e),g.edge_target(e),1)
  try:
    # existe; aresta não é ponte
    p = next(paths)
    return False
  except StopIteration:
    # não existe; aresta é ponte
    return True
    
############################
### Funções auxiliares para árvores enraizadas
def parent (v,rtree):
  inedges = list(rtree.inedges_of(v))
  if len(inedges) == 1:
    return rtree.edge_source(inedges[0])
  else: # todo vértice tem apenas um predecessor em uma árvore enraizada
    return None

def children (v,tree):
  oute = tree.outedges_of(v)
  return [tree.edge_target(e) for e in oute]

def is_root(v,tree):
  return list(tree.inedges_of(v))==[]

def is_leaf(v,tree):
  return list(tree.outedges_of(v))==[]
  
def level(tree,root,v):
  return len(next(yen_k_loopless(tree,root,v,1)).edges)

def get_rootedTree(tree,root):
  if not is_tree(tree):
    return None
  # cria instância do grafo orientado com opção dag=True
  rtree = jgrapht.create_graph(dag=True,weighted=False)
  # adiciona vértices no grafo
  rtree.add_vertices_from(tree.vertices)
  # adiciona arcos no grafo a partir das arestas de tree
  # considerando como source o terminal com menor nível (distância de root)
  for e in tree.edges:
    s = tree.edge_source(e)
    t = tree.edge_target(e)
    if level(tree,root,s) < level(tree,root,t):
      rtree.add_edge(s,t)
    else:
      rtree.add_edge(t,s)
  return rtree