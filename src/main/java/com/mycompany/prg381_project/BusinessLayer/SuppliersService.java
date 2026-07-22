package com.mycompany.prg381_project.BusinessLayer;

import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;
import com.mycompany.prg381_project.DAO.suppliersDAO;
import com.mycompany.prg381_project.model.suppliersModel;

import java.util.List;

/**
 * Business rules for Suppliers. The UI only calls these methods and
 * either updates its table or catches a BusinessException.
 */
public class SuppliersService {

    private final suppliersDAO dao = new suppliersDAO();

    public List<suppliersModel> getAllSuppliers() {
        return dao.ReadSuppliers();
    }

    public void addSupplier(String name, String contact, String phone, String email, String address)
            throws BusinessException {
        suppliersModel s = validateAndBuild(name, contact, phone, email, address);
        if (!dao.CreateSuppliers(s)) {
            throw new BusinessException("Failed to add supplier.");
        }
    }

    public void updateSupplier(int supplierId, String name, String contact, String phone, String email, String address)
            throws BusinessException {
        suppliersModel s = validateAndBuild(name, contact, phone, email, address);
        s.setSupplierID(supplierId);
        if (!dao.UpdateSuppliers(s)) {
            throw new BusinessException("Failed to update supplier.");
        }
    }

    public void deleteSupplier(int supplierId) throws BusinessException {
        if (!dao.DeleteSuppliers(supplierId)) {
            throw new BusinessException("Failed to delete supplier. It may still have materials linked to it.");
        }
    }

    private suppliersModel validateAndBuild(String name, String contact, String phone, String email, String address)
            throws BusinessException {
        String cleanName = name == null ? "" : name.trim();
        if (cleanName.isEmpty()) {
            throw new BusinessException("Supplier name is required.");
        }
        suppliersModel s = new suppliersModel();
        s.setName(cleanName);
        s.setContact(contact == null ? "" : contact.trim());
        s.setPhoneNumber(phone == null ? "" : phone.trim());
        s.setEmail(email == null ? "" : email.trim());
        s.setAddress(address == null ? "" : address.trim());
        return s;
    }
}
