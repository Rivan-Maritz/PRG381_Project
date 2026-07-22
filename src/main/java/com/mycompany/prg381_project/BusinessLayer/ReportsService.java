package com.mycompany.prg381_project.BusinessLayer;

import com.mycompany.prg381_project.DAO.materialsDAO;
import com.mycompany.prg381_project.DAO.stockissuanceDAO;
import com.mycompany.prg381_project.model.materialsModel;
import com.mycompany.prg381_project.model.stockissuanceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Business rules for the four required reports. The UI just asks for a
 * report and renders whatever rows come back - no aggregation or DAO
 * calls happen in ReportsPanel anymore.
 */
public class ReportsService {

    private final materialsDAO materialsDao = new materialsDAO();
    private final stockissuanceDAO issuanceDao = new stockissuanceDAO();

    public List<materialsModel> getInventoryReport() {
        return materialsDao.ReadAllMaterials();
    }

    public List<materialsModel> getLowStockReport() {
        return materialsDao.getLowStockMaterials();
    }

    public List<stockissuanceModel> getIssuanceHistoryReport() {
        return issuanceDao.ReadAllStockIssuance();
    }

    /** One row of the Material Usage report: a material and how much of it has been issued in total. */
    public static class MaterialUsageRow {
        public final int materialId;
        public final String materialName;
        public final int totalIssued;

        public MaterialUsageRow(int materialId, String materialName, int totalIssued) {
            this.materialId = materialId;
            this.materialName = materialName;
            this.totalIssued = totalIssued;
        }
    }

    /**
     * Total quantity issued per material. Computed here by aggregating the
     * issuance history and joining against the materials list - there's no
     * dedicated SQL aggregate query for this yet.
     */
    public List<MaterialUsageRow> getMaterialUsageReport() {
        Map<Integer, Integer> totalIssuedByMaterialId = new HashMap<>();
        for (stockissuanceModel r : issuanceDao.ReadAllStockIssuance()) {
            totalIssuedByMaterialId.merge(r.getMaterialid(), r.getQuantity(), Integer::sum);
        }

        List<MaterialUsageRow> rows = new ArrayList<>();
        for (materialsModel m : materialsDao.ReadAllMaterials()) {
            int totalIssued = totalIssuedByMaterialId.getOrDefault(m.getMaterialID(), 0);
            rows.add(new MaterialUsageRow(m.getMaterialID(), m.getMName(), totalIssued));
        }
        return rows;
    }
}
