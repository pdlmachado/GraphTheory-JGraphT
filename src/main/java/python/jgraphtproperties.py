from jgrapht.properties import is_weakly_connected
from jgrapht.algorithms.shortestpaths import yen_k_loopless
from jgrapht.views import as_masked_subgraph
from treeutil import is_root, is_leaf, children, dfs
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

# Determina se um vértice é de corte em um grafo
# Recebe como entrada o vértice, o grafo e uma árvore qualquer de busca em profundidade no grafo
def is_cutvertex (v, g):
  if (is_weakly_connected(g)):
    tree = dfs(g,0)
    if is_root(v,tree) and len(list(tree.outedges_of(v)))>=2:
      return True
    elif is_root(v,tree) or is_leaf(v,tree):
      return False
    else:
      ans = list(tree.ancestors(v))
      chi = children(v,tree)
      for f in chi:
        subtreev = list(tree.descendants(f)) + [f]
        flag = True
        for d in subtreev:
          a = all(g.contains_edge_between(a,d) == False for a in ans)
          flag = flag and all(g.contains_edge_between(a,d) == False for a in ans)
        if flag:
          return True
    return False
  return None
  