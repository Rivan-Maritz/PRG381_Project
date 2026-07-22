package com.mycompany.prg381_project.BusinessLayer;

import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;
import com.mycompany.prg381_project.DAO.cleanersDAO;
import com.mycompany.prg381_project.DAO.materialsDAO;
import com.mycompany.prg381_project.DAO.stockissuanceDAO;
import com.mycompany.prg381_project.model.cleanerModel;
import com.mycompany.prg381_project.model.materialsModel;
import com.mycompany.prg381_project.model.stockissuanceModel;

import java.sql.Date;
import java.util.List;

/**
 * Business rules for issuing stock. This is the one place in the whole
 * app where "does this even make sense" is actually enforced: quantity
 * must be positive, there must be enough stock, and the deduction plus
 * the issuance record are created together as a single operation from
 * the UI's point of view.
 *
 * Note: the two DAO calls below (UpdateMaterials, CreateStockIssuance)
 * still use their own separate connections rather than one shared JDBC
 * transaction, so this is not fully atomic against a crash between the
 * two calls. Acceptable for this project's scope, but worth knowing.
 */
public class StockIssuanceService {

    private final materialsDAO materialsDao = new materialsDAO();
    private final cleanersDAO cleanersDao = new cleanersDAO();
    private final stockissuanceDAO issuanceDao = new stockissuanceDAO();

    public List<materialsModel> getAllMaterials() {
        return materialsDao.ReadAllMaterials();
    }

    public List<cleanerModel> getAllCleaners() {
        return cleanersDao.ReadAllCleaners();
    }

    public List<stockissuanceModel> getIssuanceHistory() {
        return issuanceDao.ReadAllStockIssuance();
    }

    public materialsModel getMaterialById(int materialId) {
        return materialsDao.ReadMetrialByID(materialId);
    }

    public cleanerModel getCleanerById(int cleanerId) {
        return cleanersDao.ReadCleanerByID(cleanerId);
    }

    public void issueStock(int materialId, int cleanerId, int issuedByUserId, int quantity) throws BusinessException {
        if (quantity <= 0) {
            throw new BusinessException("Quantity must be greater than zero.");
        }
        if (issuedByUserId <= 0) {
            throw new BusinessException("No logged-in user found for this session. Please log out and back in.");
        }

        // Re-fetch the material fresh from the DB right before issuing, so we're
        // checking against the actual current stock, not a possibly-stale value.
        materialsModel material = materialsDao.ReadMetrialByID(materialId);
        if (material == null) {
            throw new BusinessException("That material no longer exists.");
        }
        if (quantity > material.getStock()) {
            throw new BusinessException("Cannot issue " + quantity + " units - only " + material.getStock() + " in stock.");
        }

        int remainingStock = material.getStock() - quantity;
        material.setStock(remainingStock);
        if (!materialsDao.UpdateMaterials(material)) {
            throw new BusinessException("Failed to update stock. Issuance was not recorded.");
        }

        stockissuanceModel issuance = new stockissuanceModel();
        issuance.setMaterialid(materialId);
        issuance.setCleanerid(cleanerId);
        issuance.setIssuedby(issuedByUserId);
        issuance.setQuantity(quantity);
        issuance.setDateIssued(new Date(System.currentTimeMillis()));
        issuance.setRemainingstock(remainingStock);

        if (!issuanceDao.CreateStockIssuance(issuance)) {
            throw new BusinessException("Stock was deducted but the issuance record failed to save. Please check the Materials screen.");
        }
    }
}
