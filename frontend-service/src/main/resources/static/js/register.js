function onLoad() {
    toggleClientData();
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