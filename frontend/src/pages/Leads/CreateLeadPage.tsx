import axios from "axios";
import React, { useState } from "react";
import { cadastrar } from "../../services/leadsService";

export function CreateLeadPage() {
    const [form, setForm] = useState({
        nome: "",
        email: "",
        telefone: ""
    });

    const [status, setStatus] = useState<"idle" | "success" | "error">("idle");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await cadastrar(form);
            setStatus("success");
            setForm({ nome: "", email: "", telefone: "" });
        } catch (error) {
            console.error(error);
            setStatus("error");
        }
    };

    return (
        <div className="container mt-5" style={{ maxWidth: "600px" }}>
            <h2 className="text-primary mb-4">Cadastrar Novo Lead</h2>

            {status === "success" && (
                <div className="alert alert-success">Lead cadastrado com sucesso!</div>
            )}
            {status === "error" && (
                <div className="alert alert-danger">Erro ao cadastrar o lead.</div>
            )}

            <form onSubmit={handleSubmit} className="bg-light p-4 rounded shadow-sm border">
                <div className="mb-3">
                    <label className="form-label">Nome</label>
                    <input
                        type="text"
                        className="form-control"
                        name="nome"
                        value={form.nome}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">E-mail</label>
                    <input
                        type="email"
                        className="form-control"
                        name="email"
                        value={form.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Telefone</label>
                    <input
                        type="text"
                        className="form-control"
                        name="telefone"
                        value={form.telefone}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary w-100">
                    Cadastrar
                </button>
            </form>
        </div>
    );
}