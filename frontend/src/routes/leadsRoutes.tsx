import { Route } from "react-router-dom";
import { LeadsPage } from "../pages/Leads/LeadsPage";
import { CreateLeadPage } from "../pages/Leads/CreateLeadPage";

export function LeadsRoutes() {
    return (
        <>
            <Route path="/" element={<CreateLeadPage />} />
            <Route path="/leads" element={<LeadsPage />} />
        </>
    );
}