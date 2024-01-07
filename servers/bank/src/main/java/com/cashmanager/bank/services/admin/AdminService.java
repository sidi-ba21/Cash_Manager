package com.cashmanager.bank.services.admin;

import com.cashmanager.bank.exceptions.AdminException;
import com.cashmanager.bank.models.Admin;
import com.cashmanager.bank.payload.request.client.AddAdminRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin create(AddAdminRequest request) {
        Admin admin = new Admin();
        admin.setLogin(request.getLogin());
        admin.setPassword(this.passwordEncoder.encode(request.getPassword()));

        return this.adminRepository.save(admin);
    }

    @Override
    public Admin find(String login, String password) throws AdminException {
        Optional<Admin> optionalAdmin = this.adminRepository.findByLogin(login);

        if (optionalAdmin.isEmpty()) {
            throw new AdminException("Account not found");
        }

        Admin admin = optionalAdmin.get();

        if (!this.passwordEncoder.matches(password, admin.getPassword())) {
            throw new AdminException("Invalid credentials");
        }

        return admin;
    }

}
