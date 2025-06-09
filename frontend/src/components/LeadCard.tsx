import type Lead from "../types/Lead";

interface Props {
    lead: Lead;
}

export function LeadCard({ lead }: Props) {
    return (
        <div className="card mb-3 shadow-sm">
            <div className="card-body">
                <h5 className="card-title">{lead.nome}</h5>
                <p className="card-text">Telefone: {lead.telefone}</p>
                <p className="card-text">Email: {lead.email}</p>
                <button className="btn btn-outline-primary btn-sm">Ver mais</button>
            </div>
        </div>
    );
}
