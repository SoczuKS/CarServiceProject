function onLoad() {
    let tabLinks = document.querySelectorAll('.tab_links');
    tabLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            openTab(event, link.getAttribute('data-tab'));
        });
    });
    toggleClientData();
}

function openTab(event, tabName) {
    let tabContent = document.getElementsByClassName("tab_content");
    for (let i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }

    let tabLinks = document.getElementsByClassName("tab_links");
    for (let i = 0; i < tabLinks.length; i++) {
        tabLinks[i].className = tabLinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    event.currentTarget.className += " active";
}

function setRequired(container, isRequired) {
    const elements = container.getElementsByTagName('input');
    for (let i = 0; i < elements.length; i++) {
        elements[i].required = isRequired;
    }
}

function toggleClientData() {
    const individualClientData = document.getElementById('individual_client_data');
    const companyClientData = document.getElementById('company_client_data');
    const isIndividual = document.getElementById('individual').checked;
    console.log("Przełączam na: " + (isIndividual ? "osobę fizyczną" : "firmę"));

    if (isIndividual) {
        individualClientData.style.display = 'block';
        companyClientData.style.display = 'none';
        setRequired(individualClientData, true);
        setRequired(companyClientData, false);
    } else {
        individualClientData.style.display = 'none';
        companyClientData.style.display = 'block';
        setRequired(individualClientData, false);
        setRequired(companyClientData, true);
    }
}
