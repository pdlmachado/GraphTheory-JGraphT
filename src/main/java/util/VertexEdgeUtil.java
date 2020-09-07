package util;

import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.util.SupplierUtil;


public class VertexEdgeUtil <V,E> {
	
	public static Supplier<DefaultVertex> createDefaultVertexSupplier () {
	    Supplier<DefaultVertex> vSupplier = new Supplier<DefaultVertex>() {
	        private int id = 0;
	        public DefaultVertex get() { 
	            return new DefaultVertex(Integer.toString(id++));
	        }
	    };
		return vSupplier;
	}
	
    public static Supplier<String> createStringVVertexSupplier () {
    	Supplier<String> vSupplier = new Supplier<String>() {
            private int id = 0;
            public String get() {
                return "v" + id++;
            }
        };
        return vSupplier;
    }

    public static Supplier<RelationshipDirectedEdge> createRelationshipDirectedEdgeSupplier()
    {
        return SupplierUtil.createSupplier(RelationshipDirectedEdge.class);
	}
    
    public static Supplier<RelationshipEdge> createRelationshipEdgeSupplier()
    {
        return SupplierUtil.createSupplier(RelationshipEdge.class);
	}
    
    public static Supplier<RelationshipWeightedEdge> createRelationshipWeightedEdgeSupplier()
    {
        return SupplierUtil.createSupplier(RelationshipWeightedEdge.class);
	}
    
	/**
	 * Os métodos a seguir retornam um vértice ou aresta cujo label eh igual ao passado como parametro.
	 * 
	 */
	public static DefaultVertex getVertexfromLabel(Set<DefaultVertex> V, String label) {
		DefaultVertex vertex = null;
		try {
			vertex = V.stream().filter(v-> v.getLabel().equals(label)).findAny().get();
		} catch (Exception e) {
			vertex = null;
		}
		return vertex;
	}
	
	public static DefaultVertex getVertexfromId(Set<DefaultVertex> V, String id) {
		DefaultVertex vertex;
		try {
			vertex = V.stream().filter(v-> v.getId().equals(id)).findAny().get();
		} catch (Exception e) {
			vertex = null;
		}
		return vertex;
	}

	public static RelationshipEdge getEdgefromLabel(Set<RelationshipEdge> edgeset, String label) {	
		RelationshipEdge edge;
		try {
			edge = edgeset.stream().filter(e-> e.getLabel().equals(label)).findAny().get();
		} catch (Exception e) {
			edge = null;
		}
		return edge; 			
	}
	
	public static RelationshipDirectedEdge getDEdgefromLabel(Set<RelationshipDirectedEdge> edgeset, String label) {	
		RelationshipDirectedEdge edge;
		try {
			edge = edgeset.stream().filter(e-> e.getLabel().equals(label)).findAny().get();
		} catch (Exception e) {
			edge = null;
		}
		return edge; 			
	}
	
	public static RelationshipWeightedEdge getWEdgefromLabel(Set<RelationshipWeightedEdge> edgeset, String label) {	
		RelationshipWeightedEdge edge;
		try {
			edge = edgeset.stream().filter(e-> e.getLabel().equals(label)).findAny().get();
		} catch (Exception e) {
			edge = null;
		}
		return edge; 			
	}
	
	public static RelationshipEdge getEdgefromVertexLabels(Set<RelationshipEdge> E, Set<DefaultVertex> V, String l1, String l2) {	
        RelationshipEdge edge;
        try {
    		DefaultVertex v1 = getVertexfromLabel(V,l1);
    		DefaultVertex v2 = getVertexfromLabel(V,l2);
    		edge = E.stream().filter(e-> (e.getV1().equals(v1) && e.getV2().equals(v2)) ||
    			                         (e.getV1().equals(v2) && e.getV2().equals(v1))).findAny().get();
        } catch (Exception e) {
        	edge = null;
        }
        return edge;
	}
	
	public static RelationshipWeightedEdge getWEdgefromVertexLabels(Set<RelationshipWeightedEdge> E, Set<DefaultVertex> V, String l1, String l2) {	
        // code is similar to getEdgefromVertexLabels - not able to generalize due to type errors
		RelationshipWeightedEdge edge;
        try {
    		DefaultVertex v1 = getVertexfromLabel(V,l1);
    		DefaultVertex v2 = getVertexfromLabel(V,l2);
    		edge = E.stream().filter(e-> (e.getV1().equals(v1) && e.getV2().equals(v2)) ||
    			                         (e.getV1().equals(v2) && e.getV2().equals(v1))).findAny().get();
        } catch (Exception e) {
        	edge = null;
        }
        return edge;
	}
	
	public static RelationshipDirectedEdge getDEdgefromVertexLabels(Set<RelationshipDirectedEdge> E, Set<DefaultVertex> V, String l1, String l2) {	
        // unique
		RelationshipDirectedEdge edge;
        try {
    		DefaultVertex v1 = getVertexfromLabel(V,l1);
    		DefaultVertex v2 = getVertexfromLabel(V,l2);
    		edge = E.stream().filter(e-> (e.getVSource().equals(v1) && e.getVTarget().equals(v2))).findAny().get();
        } catch (Exception e) {
        	edge = null;
        }
        return edge;
	}
	
}
