document.addEventListener('DOMContentLoaded', () => {

    // --- 0. Objeto de Conteúdo (Dicionário de Tradução) ---
    // Este objeto armazena todo o texto do manual, separado por idioma e pela chave de tradução (data-i18n).
    const translations = {
        'pt-BR': {
            // Header e Sidebar
            'title-main': 'Manual do Usuário - CRUD CNAE',
            'sidebar-index': 'Índice',
            // Seção 1
            'sec1-title': '1. TELA INICIAL (Consulta de CNAE da API IBGE)',
            'sec1-p1': 'Esta é a tela de entrada do sistema. Ela lista todas as Subclasses CNAE disponíveis no catálogo da API do IBGE, atuando como a visão principal dos dados brutos e permitindo o acesso às funções de gerenciamento local.',
            'sec1-img-caption': 'Visão Geral: Listagem completa dos registros obtidos da API (tabela `tableCnaes`).',
            'sec1-h3-list': '1.1. Lista de Registros da API',
            'sec1-p2': 'A tabela exibe a lista de CNAE, com as colunas "ID IBGE" e "Descrição". Estes dados são lidos diretamente da API e não representam o conteúdo do banco de dados local.',
            'sec1-h3-actions': '1.2. Ações na Tela Inicial',
            'sec1-li-update-btn': 'Botão Atualizar Lista: Aciona a função `buscarSubclassesAPI` para recarregar a lista de CNAE diretamente da API do IBGE.',
            'sec1-li-search-btn': 'Botão Buscar CNAE: Aciona a função `abrirPesquisaDetalhada`, direcionando para a Seção 2 para interagir com os dados locais (pesquisar, salvar, alterar ou excluir um registro).',

            // Seção 2 - Geral
            'sec2-title': '2. PESQUISA DETALHADA (Gerenciamento de CNAEs Locais)',
            'sec2-p1': 'A tela de Pesquisa Detalhada concentra as operações de CRUD (Criação, Leitura, Atualização e Exclusão), focando apenas nos registros já persistidos no banco de dados local.',

            // Seção 2 - Abas
            'tab-search-local': 'Pesquisar/Consultar (Local)',
            'tab-exclude': 'Excluir Selecionado', // Alterado para singular
            'tab-edit': 'Editar Selecionado',
            'tab-save': 'Salvar/Incluir (Local)',
            
            // Seção 2.1
            'sec2-1-h3': '2.1. Pesquisar e Consultar Registros (Banco Local)',
            'sec2-1-p1': 'Utilize o campo \'Digite o CNAE\' para filtrar os resultados. Ao clicar em "Pesquisar", o sistema lista os CNAE que correspondem ao filtro, buscando-os exclusivamente no banco de dados local.',
            'sec2-1-p2': 'Os registros listados (após a busca) servem como base para as demais funções de CRUD local.',

            // Seção 2.2 (Corrigido para exclusão única)
            'sec2-2-h3': '2.2. Excluir CNAE Selecionado (Confirmação)',
            'sec2-2-p1': 'O sistema permite a exclusão de apenas **um registro por vez**. Após selecionar **um CNAE** na tabela e clicar em "Excluir Selecionado", o sistema solicita a confirmação do usuário. Aprovada a ação, o registro selecionado é removido do banco de dados local, e a mensagem de sucesso ilustrada é exibida.',
            'sec2-2-p2-dialog': 'Mensagem de Sucesso Após Exclusão:',
            'sec2-2-p3-info': 'O diálogo de "Sim/Não" para confirmação da exclusão é obrigatório antes da remoção do registro selecionado.',
            
            // Seção 2.3
            'sec2-3-h3': '2.3. Editar CNAE Selecionado',
            'sec2-3-p1': 'Ao selecionar um CNAE e clicar no botão "Editar Selecionado", uma caixa de diálogo é aberta, permitindo a modificação da descrição da Subclasse, como visto abaixo.',
            'sec2-3-p2-edit': 'Diálogo de Edição de Descrição:',
            'sec2-3-p3': 'A modificação é salva no banco de dados local ao clicar em "OK" no diálogo de edição.',

            // Seção 2.4
            'sec2-4-h3': '2.4. Salvar/Incluir CNAE (Banco Local)',
            'sec2-4-p1': 'O botão "Salvar CNAEs" (localizado na parte inferior da tela de pesquisa) é utilizado para persistir dados no banco de dados local, seja um CNAE importado ou um registro editado. A imagem a seguir mostra uma lista de resultados filtrados antes de serem potencialmente salvos.',
            'sec2-4-p2-api-result': 'Resultados da Pesquisa Filtrados por "A":',
            'sec2-4-p3': 'O fluxo permite consolidar novos dados (ou importações) no banco de dados local para gerenciamento via CRUD.',
        },
        'en-US': {
            // Header and Sidebar
            'title-main': 'User Manual - CRUD CNAE',
            'sidebar-index': 'Index',
            // Section 1
            'sec1-title': '1. HOME SCREEN (CNAE API IBGE Query)',
            'sec1-p1': 'This is the system\'s entry screen. Its purpose is to list all CNAE Subclasses available in the IBGE API catalog, serving as the main view of raw data and allowing quick access to local management functions.',
            'sec1-img-caption': 'Overview: Full listing of records obtained from the API (table `tableCnaes`).',
            'sec1-h3-list': '1.1. API Record List',
            'sec1-p2': 'The table displays the list of CNAE, with the columns "IBGE ID" and "Description". This data is loaded directly from the API and does not represent the content of the local database.',
            'sec1-h3-actions': '1.2. Actions on the Home Screen',
            'sec1-li-update-btn': 'Update List Button: Triggers the `buscarSubclassesAPI` function to reload the CNAE list directly from the IBGE API.',
            'sec1-li-search-btn': 'Search CNAE Button: Triggers the `abrirPesquisaDetalhada` function, redirecting to Section 2 to interact with local data (search, save, change, or delete a record).',

            // Seção 2 - Geral
            'sec2-title': '2. DETAILED SEARCH (Local CNAE Management)',
            'sec2-p1': 'The Detailed Search screen focuses on CRUD operations (Create, Read, Update, and Delete), concentrating only on records already persisted in the local database.',

            // Section 2 - Tabs
            'tab-search-local': 'Search/Query (Local)',
            'tab-exclude': 'Exclude Selected', // Alterado para singular
            'tab-edit': 'Edit Selected',
            'tab-save': 'Save/Include (Local)',
            
            // Seção 2.1
            'sec2-1-h3': '2.1. Search and Query Records (Local Database)',
            'sec2-1-p1': 'Use the field \'Enter CNAE\' to filter results. Clicking "Search" lists CNAE matching the filter, searching exclusively in the local database.',
            'sec2-1-p2': 'The listed records (after search) serve as the basis for other local CRUD functions.',

            // Seção 2.2 (Corrigido para exclusão única)
            'sec2-2-h3': '2.2. Exclude Selected CNAE (Confirmation)',
            'sec2-2-p1': 'The system allows the deletion of only **one record at a time**. After selecting **one CNAE** in the table and clicking "Exclude Selected", the system requests user confirmation. Once approved, the selected record is removed from the local database, and the illustrated success message is displayed.',
            'sec2-2-p2-dialog': 'Success Message After Exclusion:',
            'sec2-2-p3-info': 'The "Yes/No" dialog for exclusion confirmation is mandatory before the removal of the selected record.',
            
            // Seção 2.3
            'sec2-3-h3': '2.3. Edit Selected CNAE',
            'sec2-3-p1': 'After selecting a CNAE and clicking the "Edit Selected" button, a dialog box opens, allowing modification of the Subclass description, as shown below.',
            'sec2-3-p2-edit': 'Description Edit Dialog:',
            'sec2-3-p3': 'The modification is saved to the local database by clicking "OK" in the edit dialog.',

            // Seção 2.4
            'sec2-4-h3': '2.4. Save/Include CNAE (Local Database)',
            'sec2-4-p1': 'The "Save CNAEs" button (located at the bottom of the search screen) is used to persist data in the local database, whether it\'s an imported CNAE or an edited record. The following image shows a list of filtered results before they are potentially saved.',
            'sec2-4-p2-api-result': 'Search Results Filtered by "A":',
            'sec2-4-p3': 'The flow allows consolidating new data (or imports) in the local database for CRUD management.',
        },
        'es': { 
            // Header y Barra Lateral
            'title-main': 'Manual de Usuario - CRUD CNAE',
            'sidebar-index': 'Índice',
            // Sección 1
            'sec1-title': '1. PANTALLA INICIAL (Consulta de CNAE de la API IBGE)',
            'sec1-p1': 'Esta es la pantalla de entrada del sistema. Su objetivo es listar todas las Subclases CNAE disponibles en el catálogo de la API del IBGE, sirviendo como la vista principal de los datos brutos y permitiendo el acceso a las funciones de gestión local.',
            'sec1-img-caption': 'Vista General: Listado completo de los registros obtenidos de la API (tabla `tableCnaes`).',
            'sec1-h3-list': '1.1. Lista de Registros de la API',
            'sec1-p2': 'La tabla muestra la lista de CNAE, con las columnas "ID IBGE" y "Descripción". Estos datos se leen directamente de la API y no representan el contenido de la base de datos local.',
            'sec1-h3-actions': '1.2. Acciones en la Pantalla Inicial',
            'sec1-li-update-btn': 'Botón Actualizar Lista: Activa la función `buscarSubclasesAPI` para recargar la lista de CNAE directamente de la API del IBGE.',
            'sec1-li-search-btn': 'Botón Buscar CNAE: Activa la función `abrirPesquisaDetalhada`, redirigiendo a la Sección 2 para interactuar con los datos locales (buscar, guardar, modificar o eliminar un registro).',

            // Sección 2 - General
            'sec2-title': '2. BÚSQUEDA DETALLADA (Gestión de CNAEs Locales)',
            'sec2-p1': 'La pantalla de Búsqueda Detallada concentra las operaciones CRUD (Creación, Lectura, Actualización y Eliminación), enfocándose solo en los registros ya persistidos en la base de datos local.',

            // Sección 2 - Pestañas
            'tab-search-local': 'Buscar/Consultar (Local)',
            'tab-exclude': 'Eliminar Seleccionado', // Alterado para singular
            'tab-edit': 'Editar Seleccionado',
            'tab-save': 'Guardar/Incluir (Local)',
            
            // Sección 2.1
            'sec2-1-h3': '2.1. Buscar y Consultar Registros (Base de Datos Local)',
            'sec2-1-p1': 'Utilice el campo \'Escriba el CNAE\' para filtrar los resultados. Al hacer clic en "Buscar", el sistema lista los CNAE que corresponden al filtro, buscando-los exclusivamente en la base de datos local.',
            'sec2-1-p2': 'Los registros listados (después de la búsqueda) sirven como base para las demás funciones CRUD locales.',

            // Sección 2.2 (Corrigido para exclusão única)
            'sec2-2-h3': '2.2. Eliminar CNAE Seleccionado (Confirmación)',
            'sec2-2-p1': 'El sistema permite la eliminación de solo **un registro a la vez**. Tras seleccionar **un CNAE** en la tabla y hacer clic en "Eliminar Seleccionado", el sistema solicita la confirmación del usuario. Una vez aprobada, el registro seleccionado se elimina de la base de datos local, y se muestra el mensaje de éxito ilustrado.',
            'sec2-2-p2-dialog': 'Mensaje de Éxito Después de la Eliminación:',
            'sec2-2-p3-info': 'El diálogo de "Sí/No" para la confirmación de la eliminación es obligatorio antes de la remoción del registro seleccionado.',
            
            // Sección 2.3
            'sec2-3-h3': '2.3. Editar CNAE Seleccionado',
            'sec2-3-p1': 'Al seleccionar un CNAE y hacer clic en el botón "Editar Seleccionado", se abre un cuadro de diálogo, permitiendo la modificación de la descripción de la Subclase, como se ve a continuación.',
            'sec2-3-p2-edit': 'Diálogo de Edición de Descripción:',
            'sec2-3-p3': 'La modificación se guarda en la base de datos local al hacer clic en "OK" en el diálogo de edición.',

            // Seção 2.4
            'sec2-4-h3': '2.4. Guardar/Incluir CNAE (Base de Datos Local)',
            'sec2-4-p1': 'El botón "Guardar CNAEs" (ubicado en la parte inferior de la pantalla de búsqueda) se utiliza para persistir datos en la base de datos local, ya sea un CNAE importado o un registro editado. La imagen a seguir muestra una lista de resultados filtrados antes de ser potencialmente guardados.',
            'sec2-4-p2-api-result': 'Resultados de la Búsqueda Filtrados por "A":',
            'sec2-4-p3': 'El flujo permite consolidar nuevos datos (o importações) en la base de datos local para la gestión a través de CRUD.',
        }
    };

    // --- 1. Função de Tradução Principal ---
    function setLanguage(lang) {
        const elements = document.querySelectorAll('[data-i18n]');
        const currentTranslation = translations[lang];

        elements.forEach(el => {
            const key = el.getAttribute('data-i18n');
            if (currentTranslation[key]) {
                el.innerHTML = currentTranslation[key];
            }
        });
        // Salva o idioma atual na memória do navegador (para persistir)
        localStorage.setItem('manualLang', lang);
        
        // ** Chamada obrigatória para regerar o índice após a tradução dos títulos **
        generateSidebarIndex(); 

        // Lógica para manter a aba ativa ou ativar a primeira
        const tabButtons = document.querySelectorAll('.tab-button');
        const activeTab = document.querySelector('.tab-button.active');
        
        // Remove a classe 'active' de todos os botões e conteúdos, e aplica novamente
        tabButtons.forEach(btn => btn.classList.remove('active'));
        document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));

        if (activeTab) {
            // Se havia uma aba ativa antes da tradução, reativa-a
            const activeDataTab = activeTab.getAttribute('data-tab');
            const targetButton = document.querySelector(`.tab-button[data-tab="${activeDataTab}"]`);
            if (targetButton) {
                targetButton.classList.add('active');
                document.getElementById(activeDataTab).classList.add('active');
            }
        } else if (tabButtons.length > 0) {
            // Caso contrário, ativa a primeira aba
            tabButtons[0].classList.add('active');
            const targetId = tabButtons[0].getAttribute('data-tab');
            document.getElementById(targetId).classList.add('active');
        }
    }

    // --- 2. Lógica de Carregamento e Eventos ---
    
    // 2.1. Define o idioma inicial (pega da memória ou usa pt-BR)
    const initialLang = localStorage.getItem('manualLang') || 'pt-BR';
    setLanguage(initialLang);

    // 2.2. Adiciona evento de clique aos botões de idioma
    document.getElementById('lang-selector-pt').addEventListener('click', () => setLanguage('pt-BR'));
    document.getElementById('lang-selector-en').addEventListener('click', () => setLanguage('en-US'));
    document.getElementById('lang-selector-es').addEventListener('click', () => setLanguage('es')); 


    // --- 3. Funcionalidades de Estrutura ---

    // 3.1. Gerar o Índice Dinamicamente (Função modularizada)
    function generateSidebarIndex() {
        const sidebarList = document.querySelector('#sidebar ul');
        const sections = document.querySelectorAll('.manual-section');

        // Limpa o índice antes de gerar
        sidebarList.innerHTML = ''; 

        sections.forEach(section => {
            // Pega o texto do título JÁ TRADUZIDO 
            const titleElement = section.querySelector('h2');
            const title = titleElement ? titleElement.innerText : 'Sem Título'; 
            const id = section.id;
            const listItem = document.createElement('li');
            const link = document.createElement('a');
            link.href = `#${id}`;
            link.innerText = title;
            listItem.appendChild(link);
            sidebarList.appendChild(listItem);
        });
    }

    // 3.2. Smooth Scroll para os links do índice
    // Corrigido para usar querySelector, que retorna um único elemento, garantindo que o listener funcione.
    document.querySelector('#sidebar').addEventListener('click', function(e) {
        if (e.target.tagName === 'A') {
            e.preventDefault();
            const targetId = e.target.getAttribute('href');
             // Rola para a seção
            document.querySelector(targetId).scrollIntoView({
                behavior: 'smooth'
            });
        }
    });

    // 3.3. Funcionalidade das Abas
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabContents = document.querySelectorAll('.tab-content');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Remove a classe 'active' de todos os botões e conteúdos
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabContents.forEach(content => content.classList.remove('active'));

            // Adiciona a classe 'active' ao botão clicado
            button.classList.add('active');

            // Adiciona a classe 'active' ao conteúdo correspondente
            const targetTabId = button.dataset.tab;
            const targetContent = document.getElementById(targetTabId);
            if(targetContent) {
                targetContent.classList.add('active');
            }
        });
    });
});