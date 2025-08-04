# 🔗 Repositório Git - Esul Rotas

## ✅ Repositório Configurado com Sucesso!

**GitHub**: https://github.com/pikulitomarkin/esulrotas.git
**Branch principal**: `master`
**Commit inicial**: `ed8b86a` ✅

---

## 📁 Estrutura do Repositório

```
esulrotas/
├── 📱 src/main/java/com/esul/esulrotas/    # Código Kotlin
│   ├── MainActivity.kt                      # Tela principal
│   ├── MapsActivity.kt                      # Tela do mapa
│   ├── Torre.kt                             # Modelo de dados
│   └── TorreDataLoader.kt                   # Carregador GPX
├── 🎨 src/main/res/                         # Recursos Android
│   ├── layout/                              # Layouts XML
│   ├── values/                              # Strings, cores, temas
│   └── xml/                                 # Configurações
├── 🔧 app/build.gradle                      # Configuração do módulo
├── 🔧 build.gradle                          # Configuração do projeto
├── 📖 README.md                             # Documentação principal
├── 🚀 COMPILACAO-RAPIDA.md                  # Guia de compilação
├── 🗺️ exemplo-torres.gpx                    # Arquivo GPX de teste
└── 📋 .gitignore                            # Arquivos ignorados
```

---

## 🚀 Comandos Git Essenciais

### **Clonar o repositório**
```bash
git clone https://github.com/pikulitomarkin/esulrotas.git
cd esulrotas
```

### **Fazer alterações**
```bash
# Modificar arquivos...
git add .
git commit -m "Descrição da mudança"
git push origin master
```

### **Atualizar repositório local**
```bash
git pull origin master
```

### **Verificar status**
```bash
git status
git log --oneline
```

---

## 🔄 Workflow de Desenvolvimento

### **1. Para Nova Funcionalidade**
```bash
git checkout -b feature/nova-funcionalidade
# Desenvolver...
git add .
git commit -m "Add: Nova funcionalidade"
git push origin feature/nova-funcionalidade
# Criar Pull Request no GitHub
```

### **2. Para Correção de Bug**
```bash
git checkout -b fix/correcao-bug
# Corrigir...
git add .
git commit -m "Fix: Correção do bug"
git push origin fix/correcao-bug
```

### **3. Para Atualização de Documentação**
```bash
# Diretamente no master
git add .
git commit -m "Docs: Atualização da documentação"
git push origin master
```

---

## 📝 Convenções de Commit

```bash
feat: Nova funcionalidade
fix: Correção de bug
docs: Documentação
style: Formatação (sem alteração de lógica)
refactor: Refatoração de código
test: Testes
chore: Tarefas de manutenção
```

**Exemplos:**
```bash
git commit -m "feat: Adiciona busca por proximidade"
git commit -m "fix: Corrige carregamento de GPX"
git commit -m "docs: Atualiza README com novas instruções"
```

---

## 🔧 Configuração para Novos Colaboradores

```bash
# Configurar Git
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"

# Clonar projeto
git clone https://github.com/pikulitomarkin/esulrotas.git
cd esulrotas

# Abrir no Android Studio
# File → Open → Selecionar pasta esulrotas
```

---

## 📱 Releases e Versioning

### **Versão Atual**: `v1.0.0` (Primeira versão)

### **Criar nova release**:
```bash
git tag -a v1.1.0 -m "Release v1.1.0"
git push origin v1.1.0
```

### **Versionamento semântico**:
- `v1.0.0`: Versão inicial
- `v1.0.1`: Correções de bug
- `v1.1.0`: Novas funcionalidades
- `v2.0.0`: Mudanças breaking

---

## 🎯 Próximos Passos

1. **Compilar**: Use `COMPILACAO-RAPIDA.md`
2. **Testar**: Com arquivo `exemplo-torres.gpx`
3. **Melhorar**: Adicionar novas funcionalidades
4. **Documentar**: Atualizar README conforme mudanças
5. **Release**: Criar versões estáveis

---

## 🔗 Links Úteis

- **Repositório**: https://github.com/pikulitomarkin/esulrotas
- **Issues**: https://github.com/pikulitomarkin/esulrotas/issues
- **Wiki**: https://github.com/pikulitomarkin/esulrotas/wiki
- **Releases**: https://github.com/pikulitomarkin/esulrotas/releases

---

**🎉 Repositório pronto para desenvolvimento colaborativo!**
