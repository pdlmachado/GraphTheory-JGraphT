package util;

import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.util.SupplierUtil;

public class VertexEdgeUtil {
	
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
    
	/**
	 * Os métodos a seguir retornam um vértice ou aresta cujo label eh igual ao passado como parametro.
	 * 
	 */
	public static DefaultVertex getVertexfromLabel(Set<DefaultVertex> V, String label) {	
		return V.stream().filter(v-> v.getLabel().equals(label)).findAny().get();
	}

	public static RelationshipEdge getEdgefromLabel(Set<RelationshipEdge> E, String label) {	
		return E.stream().filter(e-> e.getLabel().equals(label)).findAny().get();
	}
	
	public static RelationshipEdge getEdgefromVertexLabels(Set<RelationshipEdge> E, Set<DefaultVertex> V, String l1, String l2) {	
		DefaultVertex v1 = getVertexfromLabel(V,l1);
		DefaultVertex v2 = getVertexfromLabel(V,l2);
		return E.stream().filter(e-> (e.getV1().equals(v1) && e.getV2().equals(v2)) ||
			                         (e.getV1().equals(v2) && e.getV2().equals(v1))).findAny().get();
	}
}
