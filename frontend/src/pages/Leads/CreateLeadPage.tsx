import React, { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import { cadastrar } from "../../services/leadsService";
import "../../assets/styles.css";

export function CreateLeadPage() {
    const [form, setForm] = useState({
        nome: "",
        email: "",
        telefone: ""
    });
    const [loading, setLoading] = useState(false);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const isValid = () => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const telefoneRegex = /^\d{11}$/;
        if (form.nome.trim().length < 3) {
            toast.error('Nome deve ter ao menos 3 letras');
            return false;
        }
        if (!emailRegex.test(form.email)) {
            toast.error("E-mail inválido");
            return false;
        }
        if (!telefoneRegex.test(form.telefone)) {
            toast.error('Telefone inválido. Use o formato (99)99999-9999');
            return false;
        }
        return true;
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!isValid()) return;
        try {
            setLoading(true);
            await cadastrar(form);
            toast.success('Lead cadastrado com sucesso!');
            setForm({ nome: "", email: "", telefone: "" });
        } catch (error) {
            console.error(error);
            toast.error('Erro ao cadastrar lead. Tente novamente.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="landing-page">
            <ToastContainer position="top-right" autoClose={3000} />
            <div className="form-container">
                <div className="form-header">
                    <h1 className="headline">🚀 Campanha Nacional de Oportunidades</h1>
                    <p className="subtext">Inscreva-se para fazer parte da transformação!</p>
                </div>

                <form onSubmit={handleSubmit} noValidate className="form">
                    <div className="form-group">
                        <label htmlFor="nome">Nome</label>
                        <input
                            type="text"
                            id="nome"
                            name="nome"
                            value={form.nome}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            value={form.email}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="telefone">Telefone</label>
                        <input
                            type="text"
                            id="telefone"
                            name="telefone"
                            maxLength={11}
                            value={form.telefone}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div>
                        <button
                            type="submit"
                            className="submit-button"
                            disabled={loading}
                        >
                            {loading ? "Salvando..." : "Cadastrar Lead"}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}