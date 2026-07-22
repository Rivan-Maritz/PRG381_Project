package com.mycompany.prg381_project.BusinessLayer;

import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;
import com.mycompany.prg381_project.DAO.cleanersDAO;
import com.mycompany.prg381_project.model.cleanerModel;

import java.util.List;

/**
 * Business rules for Cleaners. The UI only calls these methods and
 * either updates its table or catches a BusinessException.
 */
public class CleanersService {

    private final cleanersDAO dao = new cleanersDAO();

    public List<cleanerModel> getAllCleaners() {
        return dao.ReadAllCleaners();
    }

    public void addCleaner(String name, String phone) throws BusinessException {
        cleanerModel c = validateAndBuild(name, phone);
        if (!dao.CreateCleaner(c)) {
            throw new BusinessException("Failed to add cleaner.");
        }
    }

    public void updateCleaner(int cleanerId, String name, String phone) throws BusinessException {
        cleanerModel c = validateAndBuild(name, phone);
        c.setId(cleanerId);
        if (!dao.UpdateCleaner(c)) {
            throw new BusinessException("Failed to update cleaner.");
        }
    }

    public void deleteCleaner(int cleanerId) throws BusinessException {
        if (!dao.DeleteCleaner(cleanerId)) {
            throw new BusinessException("Failed to delete cleaner. They may still have stock issuance records linked to them.");
        }
    }

    private cleanerModel validateAndBuild(String name, String phone) throws BusinessException {
        String cleanName = name == null ? "" : name.trim();
        if (cleanName.isEmpty()) {
            throw new BusinessException("Cleaner name is required.");
        }
        cleanerModel c = new cleanerModel();
        c.setName(cleanName);
        c.setPhoneNumber(phone == null ? "" : phone.trim());
        return c;
    }
}
