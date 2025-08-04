# Exemplo de Arquivo GPX para Esul Rotas

## Formato do Arquivo GPX

O aplicativo lê arquivos GPX com waypoints representando as torres de transmissão. Cada waypoint deve conter:

- **name**: Número da torre (ex: T001, T002, etc.)
- **lat/lon**: Coordenadas da torre em formato decimal
- **desc**: Informações da torre separadas por | (pipe)

## Formato da Descrição

A descrição deve seguir o padrão: `Linha|KM|Tipo|Circuito|Observações`

Exemplo: `LT 138kV Curitiba-Londrina|0.5|Suspensão|Circuito 1|Torre próxima à subestação`

## Exemplo de Arquivo GPX

```xml
<?xml version="1.0" encoding="UTF-8"?>
<gpx version="1.1" creator="Esul Rotas App" xmlns="http://www.topografix.com/GPX/1/1">
  <metadata>
    <name>Torres de Transmissão</name>
    <desc>Waypoints das torres da linha de transmissão</desc>
  </metadata>
  
  <wpt lat="-25.4284" lon="-49.2733">
    <name>T001</name>
    <desc>LT 138kV Curitiba-Londrina|0.5|Suspensão|Circuito 1|Torre inicial</desc>
  </wpt>
  
  <wpt lat="-25.4290" lon="-49.2720">
    <name>T002</name>
    <desc>LT 138kV Curitiba-Londrina|1.2|Ancoragem|Circuito 1|Torre de ângulo</desc>
  </wpt>
</gpx>
```

## Como Criar um Arquivo GPX

### Opção 1: Editor de Texto
1. Abra um editor de texto (Notepad++, VS Code, etc.)
2. Cole o exemplo acima
3. Modifique as coordenadas e informações das torres
4. Salve com extensão `.gpx`

### Opção 2: Aplicativos GPS
1. Use aplicativos como **GPX Editor**, **OsmAnd**, ou **Garmin BaseCamp**
2. Crie waypoints nas posições das torres
3. Configure o nome como número da torre
4. Configure a descrição no formato especificado
5. Exporte como arquivo GPX

### Opção 3: Conversão de KML/KMZ
1. Se você tem dados em formato Google Earth (KML/KMZ)
2. Use ferramentas online para converter KML para GPX
3. Ajuste os nomes e descrições conforme necessário

## Campos da Descrição

| Campo | Obrigatório | Exemplo | Descrição |
|-------|-------------|---------|-----------|
| Linha | Sim | LT 138kV Curitiba-Londrina | Nome da linha de transmissão |
| KM | Não | 0.5 | Quilometragem da torre |
| Tipo | Não | Suspensão | Tipo da torre (Suspensão, Ancoragem, Derivação) |
| Circuito | Não | Circuito 1 | Identificação do circuito |
| Observações | Não | Torre próxima à estrada | Observações adicionais |

## Exemplo Prático

Para uma torre T123 na linha de 138kV, no km 15.7, tipo suspensão:

```xml
<wpt lat="-25.1234" lon="-49.5678">
  <name>T123</name>
  <desc>LT 138kV|15.7|Suspensão|Circuito 1|Acesso pela estrada rural</desc>
</wpt>
```

## Validação do Arquivo

Antes de usar no app, verifique se:
- ✅ O arquivo tem extensão `.gpx`
- ✅ Cada waypoint tem um `name` único
- ✅ As coordenadas estão em formato decimal
- ✅ A descrição segue o padrão com separador `|`
- ✅ O arquivo é um XML válido

## Ferramentas Recomendadas

- **GPX Editor** (Android/iOS): Para criar e editar GPX
- **QGIS**: Para trabalhar com dados geográficos
- **GPS Visualizer**: Ferramenta online para conversão
- **Google My Maps**: Criar mapas e exportar para KML, depois converter
