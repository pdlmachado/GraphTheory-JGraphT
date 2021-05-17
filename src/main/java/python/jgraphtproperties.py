# Importando a JgraphT 
import jgrapht
from jgrapht.algorithms.shortestpaths import yen_k_loopless
from jgrapht.views import as_masked_subgraph
from treeutil import is_root, is_leaf, children, dfs

"""# Corte de Arestas, Aresta de Corte e Vértice de Corte"""

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
# Recebe como entrada o vértice, o grafo e uma árvore DFS qualquer
def is_cutvertex (v, g, tree):
  if not v in g.vertices:
    return None
  if is_root(v,tree) and len(list(tree.outedges_of(v)))>=2:
    return True #vértice é raiz e possui mais de um filho
  elif is_root(v,tree) or is_leaf(v,tree):
    return False #vértice é folha ou raiz com menos de 2 filhos
  else:
    #Verifica se nenhum vértice na sub-arvore onde v é raiz é adjacente em g 
    #a ancestrais de v
    ancestors = list(tree.ancestors(v))
    chi = children(v,tree)
    for f in chi:
      subtreev = list(tree.descendants(f)) + [f]
      flag = True
      for d in subtreev:
        a = all(g.contains_edge_between(a,d) == False for a in ancestors)
        flag = flag and a
      if flag:
        return True      
  return False

# Retorna um corte (conjunto) de arestas com um terminal em X e outro em Y 
def edge_cut(X, g):
  Y = [v for v in g.vertices if not v in X]
  edges = filter(lambda e: (g.edge_source(e) in X and g.edge_target(e) in Y) or
                           (g.edge_source(e) in Y and g.edge_target(e) in X),g.edges)
  return list(edges)
  
# Retorna um corte de saída (conjunto) de arcos com um terminal em X e outro em Y 
def outedge_cut(X, d):
  if all(v in d.vertices for v in X):
    Y = [v for v in d.vertices if not v in X]
    arcs = filter(lambda a: d.edge_source(a) in X and d.edge_target(a) in Y,d.edges)
    return list(arcs)
  return None  