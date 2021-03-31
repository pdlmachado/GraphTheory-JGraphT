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