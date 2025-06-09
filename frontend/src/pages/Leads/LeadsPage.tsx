import { useEffect, useState } from "react";
import { listar } from "../../services/leadsService";
import { LeadCard } from "../../components/LeadCard";
import type Lead from "../../types/Lead";


export function LeadsPage() {
    const [leads, setLeads] = useState<Lead[]>([]);

    useEffect(() => {
        async function fetchLeads() {
            try {
                const response = await listar();
                setLeads(response);
            } catch (error) {
                console.error
            }
        }
        fetchLeads();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Leads Cadastrados</h1>

            {leads.length === 0 ? (
                <div className="alert alert-info">Nenhum lead encontrado.</div>
            ) : (
                <div className="row">
                    {leads.map((lead) => (
                        <div className="col-md-4" key={lead.id}>
                            <LeadCard lead={lead} />
                        </div>
                    ))}
                </div>
            )}
        </div>
    )
}