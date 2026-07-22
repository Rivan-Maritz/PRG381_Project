package com.mycompany.prg381_project.BusinessLayer;

import java.util.Set;

/**
 * Central place that answers "can this role open this screen". Screen names
 * are plain strings so this class doesn't need to depend on the ui package -
 * callers (MainFrame, DashboardPanel) pass their own screen-name constants,
 * which just happen to match the values used here.
 *
 * Supervisor: full access to every screen.
 * Storekeeper: Dashboard, Cleaners, Stock Issuance, and Reports only -
 * blocked from Materials and Suppliers management.
 */
public class AccessControlService {

    public static final String ROLE_SUPERVISOR = "Supervisor";
    public static final String ROLE_STOREKEEPER = "Storekeeper";

    // Screens a Storekeeper is allowed to open, beyond Login/Dashboard
    // (which are always reachable once authenticated).
    private static final Set<String> STOREKEEPER_ALLOWED_SCREENS = Set.of(
        "DASHBOARD", "CLEANERS", "STOCK_ISSUANCE", "REPORTS"
    );

    /**
     * @param role   the logged-in user's role (as stored in the Users table)
     * @param screen the screen identifier being requested
     * @return true if that role is permitted to open that screen
     */
    public boolean canAccess(String role, String screen) {
        if (ROLE_SUPERVISOR.equals(role)) {
            return true;
        }
        if (ROLE_STOREKEEPER.equals(role)) {
            return STOREKEEPER_ALLOWED_SCREENS.contains(screen);
        }
        // Unrecognized or missing role: deny by default rather than fail open.
        return false;
    }
}
