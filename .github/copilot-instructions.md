# Instruções de Desenvolvimento - Torres Rotas

<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

## Sobre o Projeto

Este é um aplicativo Android desenvolvido em Kotlin para localização de torres de transmissão. O app permite que o usuário digite o número de uma torre e visualize o caminho até ela no mapa.

## Arquitetura e Tecnologias

- **Linguagem**: Kotlin
- **Framework**: Android SDK
- **Mapas**: Google Maps API
- **Leitura de Planilhas**: Apache POI
- **Localização**: Google Play Services Location
- **Arquitetura**: Padrão MVVM com ViewBinding

## Estrutura do Projeto

- `Torre.kt`: Modelo de dados representando uma torre de transmissão
- `TorreDataLoader.kt`: Classe responsável por carregar dados de planilhas Excel
- `MainActivity.kt`: Tela principal onde o usuário insere o número da torre
- `MapsActivity.kt`: Tela do mapa que mostra a localização da torre e rota

## Funcionalidades Principais

1. **Carregamento de Dados**: Lê planilhas Excel (.xlsx) com informações das torres
2. **Busca por Torre**: Localiza uma torre específica pelo número
3. **Visualização no Mapa**: Mostra a torre no Google Maps
4. **Navegação**: Calcula e exibe rota até a torre
5. **Geolocalização**: Obtém posição atual do usuário

## Formato da Planilha Esperado

Colunas da planilha Excel:
- **A**: Número da Torre
- **B**: Linha de Transmissão
- **C**: Quilometragem (KM)
- **D**: Latitude
- **E**: Longitude  
- **F**: Tipo (opcional)
- **G**: Circuito (opcional)
- **H**: Observações (opcional)

## Configurações Importantes

- **Google Maps API**: Necessário configurar chave de API no arquivo `strings.xml`
- **Permissões**: App requer permissões de localização e leitura de arquivos
- **Compatibilidade**: Suporta Android API 24+ (Android 7.0)

## Guidelines de Desenvolvimento

1. Mantenha consistência com o padrão de nomenclatura existente
2. Use ViewBinding para acesso às views
3. Implemente tratamento de erros adequado
4. Siga as práticas de Material Design
5. Teste sempre com dados reais de planilhas

## Dependências Principais

- Google Maps: Para visualização de mapas e navegação
- Apache POI: Para leitura de arquivos Excel
- Google Play Services: Para serviços de localização
- Material Design Components: Para UI consistente
