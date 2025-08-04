# Migração do Projeto Web para Android

## Comparação: Projeto Original vs App Android

### Projeto Original (Web)
- **Entrada**: Quilometragem (KM) da linha
- **Dados**: Planilhas Excel + arquivos GPX
- **Saída**: Localização da torre mais próxima do KM informado
- **Interface**: Web (HTML/CSS/JavaScript)

### App Android (Novo)
- **Entrada**: Número da torre
- **Dados**: Arquivos GPX com waypoints das torres
- **Saída**: Localização exata da torre + navegação GPS
- **Interface**: Android nativo (Kotlin)

## Adaptações Realizadas

### 1. **Mudança de Lógica de Busca**
```kotlin
// Projeto original: busca por KM
function encontrarTorrePorKM(km) { ... }

// App Android: busca por número
fun buscarTorrePorNumero(numero: String): Torre? { ... }
```

### 2. **Formato de Dados**
```xml
<!-- GPX com waypoints das torres -->
<wpt lat="-25.4284" lon="-49.2733">
  <name>T001</name>
  <desc>LT 138kV|0.5|Suspensão|Circuito 1|Observações</desc>
</wpt>
```

### 3. **Funcionalidades Adicionadas**
- ✅ Navegação GPS integrada
- ✅ Cálculo automático de distância
- ✅ Interface touch-friendly
- ✅ Permissões de localização
- ✅ Carregamento de arquivos móvel

## Mantendo Compatibilidade

Para manter os dados do projeto original, você pode:

### Opção 1: Converter Dados Existentes
1. Extrair coordenadas das planilhas Excel
2. Criar waypoints GPX para cada torre
3. Incluir informações na descrição

### Opção 2: Usar Ambos os Formatos
```kotlin
// Modificar TorreDataLoader para suportar ambos
class TorreDataLoader {
    fun carregarDeGPX(uri: Uri): Result<List<Torre>> { ... }
    fun carregarDePlanilha(uri: Uri): Result<List<Torre>> { ... }
}
```

## Exemplo de Conversão

### Dados da Planilha Original:
| Torre | Linha | KM | Lat | Lng |
|-------|-------|----|-----|-----|
| T001 | LT 138kV | 0.5 | -25.4284 | -49.2733 |

### Waypoint GPX Equivalente:
```xml
<wpt lat="-25.4284" lon="-49.2733">
  <name>T001</name>
  <desc>LT 138kV|0.5|||</desc>
</wpt>
```

## Vantagens da Migração

1. **Interface Nativa**: Melhor experiência no celular
2. **GPS Integrado**: Navegação automática até a torre
3. **Offline Capability**: Dados carregados ficam em memória
4. **Busca Direta**: Não precisa calcular KM, busca direta por número
5. **Integração com Google Maps**: Navegação turn-by-turn

## Próximos Passos

1. **Teste com Dados Reais**: Use seus dados GPX existentes
2. **Customize a Interface**: Ajuste cores e textos conforme necessário
3. **Adicione Funcionalidades**: Como filtros por linha, busca por proximidade
4. **Deploy**: Publique na Google Play Store

## Scripts de Conversão (Opcional)

Se você quiser automatizar a conversão de Excel para GPX:

```python
# Exemplo de script Python para conversão
import pandas as pd
import xml.etree.ElementTree as ET

def excel_para_gpx(arquivo_excel, arquivo_gpx):
    df = pd.read_excel(arquivo_excel)
    
    # Criar estrutura GPX
    gpx = ET.Element("gpx", version="1.1")
    
    for _, row in df.iterrows():
        wpt = ET.SubElement(gpx, "wpt", 
                           lat=str(row['Latitude']), 
                           lon=str(row['Longitude']))
        
        ET.SubElement(wpt, "name").text = row['Torre']
        desc = f"{row['Linha']}|{row['KM']}|{row.get('Tipo', '')}|{row.get('Circuito', '')}|{row.get('Obs', '')}"
        ET.SubElement(wpt, "desc").text = desc
    
    # Salvar arquivo
    tree = ET.ElementTree(gpx)
    tree.write(arquivo_gpx, encoding='utf-8', xml_declaration=True)
```

O app Android está pronto e mantém a essência do seu projeto original, mas com interface mobile e funcionalidades aprimoradas para o Esul Rotas!
