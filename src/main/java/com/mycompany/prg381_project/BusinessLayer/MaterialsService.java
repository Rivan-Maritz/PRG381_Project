package com.mycompany.prg381_project.BusinessLayer;

import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;
import com.mycompany.prg381_project.DAO.materialsDAO;
import com.mycompany.prg381_project.DAO.suppliersDAO;
import com.mycompany.prg381_project.model.materialsModel;
import com.mycompany.prg381_project.model.suppliersModel;

import java.sql.Date;
import java.util.List;

/**
 * Business rules for Materials. The UI only calls these methods and
 * either updates its table or catches a BusinessException - all field
 * parsing/validation and DAO calls happen here.
 */
public class MaterialsService {

    private final materialsDAO dao = new materialsDAO();
    private final suppliersDAO suppliersDao = new suppliersDAO();

    public List<materialsModel> getAllMaterials() {
        return dao.ReadAllMaterials();
    }

    public List<materialsModel> getLowStockMaterials() {
        return dao.getLowStockMaterials();
    }

    public List<materialsModel> searchMaterials(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return dao.ReadAllMaterials();
        }
        return dao.searchMaterialsByName(keyword.trim());
    }

    public List<suppliersModel> getAllSuppliers() {
        return suppliersDao.ReadSuppliers();
    }

    public String getSupplierName(int supplierId) {
        suppliersModel s = suppliersDao.ReadSuppliers(supplierId);
        return s != null ? s.getName() : ("#" + supplierId);
    }

    public void addMaterial(String name, String type, String stockText, String reorderText, Integer supplierId)
            throws BusinessException {
        materialsModel mm = validateAndBuild(name, type, stockText, reorderText, supplierId);
        mm.setDateAdded(new Date(System.currentTimeMillis()));
        if (!dao.CreateMetrial(mm)) {
            throw new BusinessException("Failed to add material.");
        }
    }

    public void updateMaterial(int materialId, String name, String type, String stockText, String reorderText, Integer supplierId)
            throws BusinessException {
        materialsModel mm = validateAndBuild(name, type, stockText, reorderText, supplierId);
        mm.setMaterialID(materialId);
        if (!dao.UpdateMaterials(mm)) {
            throw new BusinessException("Failed to update material.");
        }
    }

    public void deleteMaterial(int materialId) throws BusinessException {
        if (!dao.DeleteMaterials(materialId)) {
            throw new BusinessException("Failed to delete material. It may still have stock issuance records linked to it.");
        }
    }

    private materialsModel validateAndBuild(String name, String type, String stockText, String reorderText, Integer supplierId)
            throws BusinessException {
        String cleanName = name == null ? "" : name.trim();
        if (cleanName.isEmpty()) {
            throw new BusinessException("Material name is required.");
        }
        if (supplierId == null) {
            throw new BusinessException("Please select a supplier. If none exist yet, add one first on the Suppliers screen.");
        }

        int stock;
        int reorderLevel;
        try {
            stock = (stockText == null || stockText.trim().isEmpty()) ? 0 : Integer.parseInt(stockText.trim());
            reorderLevel = (reorderText == null || reorderText.trim().isEmpty()) ? 10 : Integer.parseInt(reorderText.trim());
        } catch (NumberFormatException ex) {
            throw new BusinessException("Stock and Reorder Level must be whole numbers.");
        }
        if (stock < 0 || reorderLevel < 0) {
            throw new BusinessException("Stock and Reorder Level cannot be negative.");
        }

        materialsModel mm = new materialsModel();
        mm.setMName(cleanName);
        mm.setType(type == null ? "" : type.trim());
        mm.setStock(stock);
        mm.setReorderLevel(reorderLevel);
        mm.setSupplierid(supplierId);
        return mm;
    }
}
