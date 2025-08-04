# Esul Rotas - Aplicativo Android

Um aplicativo Android desenvolvido em Kotlin para localização e navegação até torres de transmissão de energia elétrica.

## 📱 Funcionalidades

- **Busca por Torre**: Digite o número da torre e encontre sua localização exata
- **Carregamento de Dados**: Importa dados de arquivos GPX com waypoints das torres  
- **Visualização no Mapa**: Exibe a torre no Google Maps com marcador personalizado
- **Navegação GPS**: Calcula e mostra o caminho até a torre selecionada
- **Informações Detalhadas**: Exibe dados como linha de transmissão, quilometragem e coordenadas
- **Localização Atual**: Obtém a posição atual do usuário para calcular distâncias

## 🛠️ Tecnologias Utilizadas

- **Kotlin** - Linguagem principal
- **Android SDK** - Framework de desenvolvimento
- **Google Maps API** - Mapas e navegação
- **JPX Library** - Leitura de arquivos GPX
- **Google Play Services Location** - Serviços de localização
- **Material Design Components** - Interface moderna

## 📋 Pré-requisitos

- Android Studio Arctic Fox ou superior
- Android SDK API 24+ (Android 7.0)
- Chave de API do Google Maps
- Dispositivo Android ou emulador com Google Play Services

## ⚙️ Configuração

### 1. Clone o repositório
```bash
git clone [URL_DO_REPOSITORIO]
cd esul-rotas
```

### 2. Abra no Android Studio
1. **Download**: https://developer.android.com/studio
2. **File → Open** → Selecione a pasta do projeto
3. **Aguarde** a sincronização do Gradle
4. **Build → Build Project** para compilar

### 3. Configure a API do Google Maps
1. Obtenha uma chave de API do Google Maps no [Google Cloud Console](https://console.cloud.google.com/)
2. Edite o arquivo `src/main/res/values/strings.xml`
3. Substitua `YOUR_GOOGLE_MAPS_API_KEY_HERE` pela sua chave real:

```xml
<string name="google_maps_key">SUA_CHAVE_AQUI</string>
```

### 4. Formato do Arquivo GPX
Crie um arquivo GPX com waypoints representando as torres:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<gpx version="1.1" creator="Torres App">
  <wpt lat="-25.4284" lon="-49.2733">
    <name>T001</name>
    <desc>LT 138kV Curitiba-Londrina|0.5|Suspensão|Circuito 1|Torre próxima à subestação</desc>
  </wpt>
  <wpt lat="-25.4290" lon="-49.2720">
    <name>T002</name>
    <desc>LT 138kV Curitiba-Londrina|1.2|Ancoragem|Circuito 1|Torre de ângulo</desc>
  </wpt>
</gpx>
```

**Formato da descrição**: `Linha|KM|Tipo|Circuito|Observações` (separado por |)

## 🚀 Como Usar

1. **Carregue os Dados**
   - Toque em "Carregar GPX"
   - Selecione o arquivo GPX com os waypoints das torres
   - Aguarde a confirmação de carregamento

2. **Busque uma Torre**
   - Digite o número da torre desejada
   - Toque em "Buscar Torre"
   - Visualize as informações da torre encontrada

3. **Navegue até a Torre**
   - Toque em "Centralizar no Mapa"
   - No mapa, toque no marcador da torre
   - Escolha abrir no Google Maps para navegação

## 📦 Estrutura do Projeto

```
src/main/java/com/esul/torresrotas/
├── MainActivity.kt          # Tela principal
├── MapsActivity.kt         # Tela do mapa
├── Torre.kt               # Modelo de dados
├── TorreDataLoader.kt     # Carregador de arquivos GPX

src/main/res/
├── layout/
│   ├── activity_main.xml    # Layout da tela principal
│   └── activity_maps.xml    # Layout do mapa
├── values/
│   ├── strings.xml         # Textos do app
│   ├── colors.xml          # Cores
│   └── themes.xml          # Temas
└── AndroidManifest.xml     # Configurações da aplicação
```

## 🔒 Permissões

O aplicativo solicita as seguintes permissões:

- **ACCESS_FINE_LOCATION**: Para obter localização precisa via GPS
- **ACCESS_COARSE_LOCATION**: Para obter localização aproximada
- **INTERNET**: Para carregar mapas e dados
- **READ_EXTERNAL_STORAGE**: Para ler arquivos GPX

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🆘 Solução de Problemas

### Torre não encontrada
- Verifique se o número da torre está correto
- Confirme se os dados foram carregados corretamente

### Erro ao carregar GPX
- Verifique se o arquivo é um GPX válido
- Confirme se os waypoints têm nome (número da torre)
- Verifique se as coordenadas estão corretas

### Mapa não carrega
- Verifique se a chave da API do Google Maps está configurada
- Confirme se há conexão com a internet
- Verifique se o Google Play Services está atualizado

---

Desenvolvido para facilitar a localização e manutenção de torres de transmissão de energia elétrica.
