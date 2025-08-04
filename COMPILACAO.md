# Manual de Compilação - Esul Rotas

## ✅ Status do Projeto

**Estrutura verificada**: ✅ Todos os arquivos estão corretos
**Sintaxe Kotlin**: ✅ Sem erros de compilação
**Configuração Gradle**: ✅ Build scripts configurados
**Recursos Android**: ✅ Layouts, strings e temas criados

## 🛠️ Pré-requisitos para Compilação

Para compilar o projeto Esul Rotas, você precisa:

### 1. **Android Studio**
- Download: https://developer.android.com/studio
- Versão recomendada: Android Studio Flamingo ou superior
- Inclui automaticamente: Android SDK, Gradle, Kotlin

### 2. **Configurações Necessárias**
```bash
# Versões recomendadas:
- Android SDK: API 34 (Android 14)
- Gradle: 8.0+
- Kotlin: 1.9.0+
- Java: 17 (já instalado ✅)
```

## 🚀 Passos para Compilação

### **Opção 1: Usando Android Studio (Recomendado)**

1. **Abra o Android Studio**
2. **File → Open** → Selecione a pasta `Esul Rotas`
3. **Aguarde a sincronização** do Gradle (primeira vez pode demorar)
4. **Configure a API Key** do Google Maps:
   ```xml
   <!-- src/main/res/values/strings.xml -->
   <string name="google_maps_key">SUA_API_KEY_AQUI</string>
   ```
5. **Build → Build Bundle(s) / APK(s) → Build APK(s)**

### **Opção 2: Linha de Comando**

```bash
# No diretório do projeto:
cd "C:\Users\Ana\Desktop\Esul Rotas"

# Compilar (se tiver Gradle instalado):
gradle assembleDebug

# Ou usando Gradle Wrapper:
./gradlew assembleDebug    # Linux/Mac
gradlew.bat assembleDebug  # Windows
```

## 🔧 Configuração da API do Google Maps

**IMPORTANTE**: O app precisa de uma chave de API do Google Maps:

1. **Acesse**: https://console.cloud.google.com/
2. **Crie um projeto** ou use existente
3. **Ative a API**: Maps SDK for Android
4. **Crie credenciais**: API Key
5. **Configure restrições**: Apenas para Android
6. **Cole a chave** em `src/main/res/values/strings.xml`:

```xml
<string name="google_maps_key">AIzaSyD4iE4xgWnEO...</string>
```

## 📱 Testando o App

### **Emulador Android**
1. **Android Studio → AVD Manager**
2. **Create Virtual Device**
3. **Escolha um dispositivo** (ex: Pixel 7)
4. **API Level 34** (Android 14)
5. **Run** → Selecione o emulador

### **Dispositivo Físico**
1. **Ative Depuração USB** no dispositivo
2. **Conecte via USB**
3. **Run** → Selecione seu dispositivo

## 🗂️ Arquivos de Saída

Após a compilação bem-sucedida:

```
app/build/outputs/apk/debug/
├── app-debug.apk          # APK para instalação
└── output-metadata.json   # Metadados do build
```

## 🔍 Verificação Pré-Compilação

**Status atual do projeto**:

✅ **Arquivos Kotlin**: Sem erros de sintaxe
✅ **Layouts XML**: Estrutura correta
✅ **AndroidManifest**: Permissões configuradas
✅ **Build Gradle**: Dependências corretas
✅ **Recursos**: Strings, cores e temas
⚠️ **API Key**: Precisa ser configurada

## 🆘 Possíveis Problemas

### **Erro: "SDK not found"**
```bash
# Solução: Configure ANDROID_HOME
export ANDROID_HOME=/path/to/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### **Erro: "Build failed"**
1. **Clean Project**: Build → Clean Project
2. **Rebuild**: Build → Rebuild Project
3. **Sync**: File → Sync Project with Gradle Files

### **Erro: "Maps not loading"**
- Verifique se a API Key está correta
- Confirme se a API Maps está ativada
- Verifique restrições da API Key

## 📦 Gerando APK de Release

Para distribuição:

```bash
# APK assinado para release
./gradlew assembleRelease

# ou AAB (recomendado para Play Store)
./gradlew bundleRelease
```

## 🎯 Próximos Passos

1. **Compile no Android Studio**
2. **Teste com arquivo GPX real**
3. **Configure cores/temas** se necessário
4. **Teste navegação GPS**
5. **Publique na Play Store** (opcional)

---

**💡 Dica**: Use o Android Studio para uma experiência completa de desenvolvimento. Ele cuidará automaticamente do Gradle, SDK e dependências!
