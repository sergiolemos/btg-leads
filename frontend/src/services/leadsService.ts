import axios from "axios";
import type Lead from "../types/Lead";

const API_BASE = "http://localhost:8080";

export async function listar(): Promise<Lead[]> {
    const response = await axios.get(`${API_BASE}/leads`);
    return response.data;
}

export async function cadastrar(data: Omit<Lead, "id" | "dataCadastro">): Promise<Lead> {
    const response = await axios.post(`${API_BASE}/leads`,data,{
        headers:{
            "Content-Type":"application/json"
        }
    });
    return response.data;
}