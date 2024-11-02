// usuario.model.ts
export class Usuario {
  id: number;
  nome: string;
  email: string;
  senha: string;
  atleticaId: number;
  role: 'VISUALIZADOR' | 'ADMIN' | 'ATLETICA'; 

  constructor(id: number, nome: string, email: string, senha: string, atleticaId: number, role: 'VISUALIZADOR' | 'ADMIN' | 'ATLETICA') {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.atleticaId = atleticaId;
    this.role = role;
  }
}
