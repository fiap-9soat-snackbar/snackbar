// Sample Cooking data based on Cooking.java model

const cookingSamples = [
  {
    id: "1234567890abcdef12345678",
    cpfCliente: "123.456.789-00",
    clientName: "João Silva",
    dataHoraPedido: "2023-05-10T14:30:00",
    items: [
      { name: "X-Burger", quantity: 2, customization: "Sem cebola" },
      { name: "Batata Frita", quantity: 1, customization: "Tamanho grande" }
    ],
    status: "EM_PREPARACAO"
  },
  {
    id: "234567890abcdef123456789",
    cpfCliente: "987.654.321-00",
    clientName: "Maria Oliveira",
    dataHoraPedido: "2023-05-10T15:45:00",
    items: [
      { name: "Pizza Margherita", quantity: 1, customization: "Borda recheada" },
      { name: "Refrigerante", quantity: 2, customization: "Sem gelo" }
    ],
    status: "PRONTO"
  },
  {
    id: "345678901abcdef234567890",
    cpfCliente: "456.789.012-34",
    clientName: "Carlos Santos",
    dataHoraPedido: "2023-05-10T16:20:00",
    items: [
      { name: "Salada Caesar", quantity: 1, customization: "Sem croutons" },
      { name: "Suco Natural", quantity: 1, customization: "Laranja com morango" }
    ],
    status: "EM_ENTREGA"
  },
  {
    id: "456789012abcdef345678901",
    cpfCliente: "789.012.345-67",
    clientName: "Ana Ferreira",
    dataHoraPedido: "2023-05-10T17:10:00",
    items: [
      { name: "Prato do Dia", quantity: 2, customization: "Vegetariano" },
      { name: "Sobremesa", quantity: 2, customization: "Pudim" }
    ],
    status: "FINALIZADO"
  },
  {
    id: "567890123abcdef456789012",
    cpfCliente: "012.345.678-90",
    clientName: "Pedro Almeida",
    dataHoraPedido: "2023-05-10T18:00:00",
    items: [
      { name: "Hot Dog", quantity: 3, customization: "Molho extra" },
      { name: "Milkshake", quantity: 1, customization: "Chocolate com menta" }
    ],
    status: "RECEBIDO"
  }
];

module.exports = cookingSamples;