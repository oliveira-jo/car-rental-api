package com.oliveira.carrentalapi.config;

import com.oliveira.carrentalapi.domain.models.Category;
import com.oliveira.carrentalapi.domain.models.Vehicle;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.repositories.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements ApplicationRunner {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Override
  public void run(ApplicationArguments args) {

    if (categoryRepository.count() > 0) {
      return; // evita duplicar dados ao reiniciar a aplicação
    }

    System.out.println("----------------------------- RUN SEED DATABASE -----------------------------");

    // -----------------------------
    // CATEGORY - POPULAR
    // -----------------------------
    Category popular = new Category(
        null,
        "Popular",
        "> Aluguel de carros, sem burocracia, é só com a CAR-RENTAL! > Aqui você reserva online o seu veículo para seus passeios, sem burocracia e com rapidez. Informe o período desejado, selecione os opcionais, adicione no carrinho e prossiga para o pagamento. Veículos disponíveis: Fiat Uno, Renault Sandero, VW Voyage, Renault Kwid, Fiat Argo ou Hyundai HB20 Abaixo seguem as informações e condições de entrega do veículo que você reservar. A imagem do veículo é meramente ilustrativa, ao alugar um popular você poderá receber um dos veículos que estão classificados nesta categoria, conforme a disponibilidade do nosso estoque. * Todos os veículos possuem diária com até 100km, consulte valores para km excedente. > SEGUROS: > Parcial: todos os veículos locados na Goularte Tur possuem cobertura de seguro parcial, sem custo adicional, essa cobertura não inclui seguro contra terceiros. Cobertura contra terceiros opcional: O seguro contra terceiros pode ser adicionado na sua locação, este seguro cobre danos no valor de até R$ 20.000,00 contra terceiros. > ENTREGAS: > As entregas são feitas mediante confirmação via WhatsApp, no turno da manhã, entre 8:00 e 12:00 nas cidades de Gramado e Canela ( hotéis, pousadas e aptos por temporada) sem custo a partir de duas diárias, zona rural ou várzea grande não realizamos entregas. - O modelo do veículo varia conforme a disponibilidade do dia, podendo ser consultado via whatsapp no dia da entrega. > REQUISITOS PARA LOCAÇÃO > Para alugar um veículo conosco, o Locatário deverá: Apresentar a Carteira de Habilitação válida e emitida em território nacional, sendo aceito pela locadora o documento original impresso ou o arquivo exportado do aplicativo da Carteira Digital de Trânsito. Apresentar um cartão de crédito nominal, com a gravação do nome do Locatário, para a operação de pré-autorização. Não serão aceitos para essa operação, cartão de crédito de terceiros, pré-pago ou virtual, podendo o parcelamento da pré-autorização ocorrer de acordo com as condições oferecidas pela Locadora; e ter as suas informações cadastrais e análise de crédito aprovadas pela Locadora. Diária de locação de veículo - corresponde ao período mínimo de 24 (vinte quatro) horas para a locação de veículos. Diária, Tolerâncias e Diárias extras – Diária é o período de 24 (vinte e quatro) horas contados da assinatura do Demonstrativo de Contrato. Após o término da diária, há tolerância de 20 minutos para ocorrer a devolução do veículo alugado. Após esse período, ocorrerá a cobrança de diária extra. > INFORMAÇÕES CADASTRAIS > ATENÇÃO: Para sua comodidade e segurança, as informações inseridas em seu cadastro estão sujeitas à confirmação após qualquer reserva realizada na locadora. Caso haja qualquer divergência dos dados que possa acarretar em problemas na reserva, você será informado através do seu e-mail cadastrado.",
        2,
        5,
        true,
        89.9F);
    categoryRepository.save(popular);

    // -----------------------------
    // CATEGORY - FAMÍLIA
    // -----------------------------
    Category familia = new Category(
        null,
        "Família",
        "> Aluguel de carros, sem burocracia, é só com a RENTAL-CAR! > Aqui você reserva online o seu veículo para seus passeios, sem burocracia e com rapidez.Informe o período desejado, selecione os opcionais, adicione no carrinho e prossiga para o pagamento. Abaixo seguem as informações e condições de entrega do veículo que você reservar. A imagem do veículo é meramente ilustrativa, ao efetuar o pagamento você poderá escolher entre uma das opções abaixo, conforme a disponibilidade do dia: Veículo Família a partir de R$ 690: GM Spin Automática 7 Lugares * Todos os veículos possuem diária com até 100km, consulte valores para km excedente. > SEGUROS: > Parcial: todos os veículos locados na Goularte Tur possuem cobertura de seguro parcial, sem custo adicional, essa cobertura não inclui seguro contra terceiros. Cobertura contra terceiros opcional: O seguro contra terceiros pode ser adicionado na sua locação, este seguro cobre danos no valor de até R$ 20.000,00 contra terceiros. > ENTREGAS:  > As entregas são feitas mediante confirmação via WhatsApp, no turno da manhã, entre 8:00 e 12:00 nas cidades de Gramado e Canela ( hotéis, pousadas e aptos por temporada) sem custo a partir de duas diárias, zona rural ou várzea grande não realizamos entregas. - O modelo do veículo varia conforme a disponibilidade do dia, podendo ser consultado via whatsapp no dia da entrega. > REQUISITOS PARA LOCAÇÃO > Para alugar um veículo conosco, o Locatário deverá: Apresentar a Carteira de Habilitação válida e emitida em território nacional, sendo aceito pela locadora o documento original impresso ou o arquivo exportado do aplicativo da Carteira Digital de Trânsito. Apresentar um cartão de crédito nominal, com a gravação do nome do Locatário, para a operação de pré-autorização. Não serão aceitos para essa operação, cartão de crédito de terceiros, pré-pago ou virtual, podendo o parcelamento da pré-autorização ocorrer de acordo com as condições oferecidas pela Locadora; e ter as suas informações cadastrais e análise de crédito aprovadas pela Locadora. Diária de locação de veículo - corresponde ao período mínimo de 24 (vinte quatro) horas para a locação de veículos. Diária, Tolerâncias e Diárias extras – Diária é o período de 24 (vinte e quatro) horas contados da assinatura do Demonstrativo de Contrato. Após o término da diária, há tolerância de 20 minutos para ocorrer a devolução do veículo alugado. Após esse período, ocorrerá a cobrança de diária extra. > INFORMAÇÕES CADASTRAIS > ATENÇÃO: Para sua comodidade e segurança, as informações inseridas em seu cadastro estão sujeitas à confirmação após qualquer reserva realizada na locadora. Caso haja qualquer divergência dos dados que possa acarretar em problemas na reserva, você será informado através do seu e-mail cadastrado.",
        2,
        7,
        true,
        690.0F);
    categoryRepository.save(familia);

    // -----------------------------
    // CATEGORY - PREMIUM
    // -----------------------------
    Category premium = new Category(
        null,
        "Premium",
        "> Aluguel de carros, sem burocracia, é só com a CAR-RENTARL! > Aqui você reserva online o seu veículo para seus passeios, sem burocracia e com rapidez. Informe o período desejado, selecione os opcionais, adicione no carrinho e prossiga para o pagamento. Abaixo seguem as informações e condições de entrega do veículo que você reservar. A imagem do veículo é meramente ilustrativa, ao efetuar o pagamento você poderá escolher entre uma das opções abaixo, conforme a disponibilidade do dia: Veiculos como: Onix Premier Turbo, Fiat Pulse Impetus Turbo a partir de R$ 350,00  * Todos os veículos possuem diária com até 100km, consulte valores para km excedente. > SEGUROS: > Parcial: todos os veículos locados na Goularte Tur possuem cobertura de seguro parcial, sem custo adicional, essa cobertura não inclui seguro contra terceiros. Cobertura contra terceiros opcional: O seguro contra terceiros pode ser adicionado na sua locação, este seguro cobre danos no valor de até R$ 20.000,00 contra terceiros. > ENTREGAS: > As entregas são feitas mediante confirmação via WhatsApp, no turno da manhã, entre 8:00 e 12:00 nas cidades de Gramado e Canela ( hotéis, pousadas e aptos por temporada) sem custo a partir de duas diárias, zona rural ou várzea grande não realizamos entregas. - O modelo do veículo varia conforme a disponibilidade do dia, podendo ser consultado via whatsapp no dia da entrega. > REQUISITOS PARA LOCAÇÃO > Para alugar um veículo conosco, o Locatário deverá: Apresentar a Carteira de Habilitação válida e emitida em território nacional, sendo aceito pela locadora o documento original impresso ou o arquivo exportado do aplicativo da Carteira Digital de Trânsito. Apresentar um cartão de crédito nominal, com a gravação do nome do Locatário, para a operação de pré-autorização. Não serão aceitos para essa operação, cartão de crédito de terceiros, pré-pago ou virtual, podendo o parcelamento da pré-autorização ocorrer de acordo com as condições oferecidas pela Locadora; e ter as suas informações cadastrais e análise de crédito aprovadas pela Locadora. Diária de locação de veículo - corresponde ao período mínimo de 24 (vinte quatro) horas para a locação de veículos. Diária, Tolerâncias e Diárias extras – Diária é o período de 24 (vinte e quatro) horas contados da assinatura do Demonstrativo de Contrato. Após o término da diária, há tolerância de 20 minutos para ocorrer a devolução do veículo alugado. Após esse período, ocorrerá a cobrança de diária extra.  > INFORMAÇÕES CADASTRAIS > ATENÇÃO: Para sua comodidade e segurança, as informações inseridas em seu cadastro estão sujeitas à confirmação após qualquer reserva realizada na locadora. Caso haja qualquer divergência dos dados que possa acarretar em problemas na reserva, você será informado através do seu e-mail cadastrado.",
        2,
        5,
        true,
        349.0F);
    categoryRepository.save(premium);

    // -----------------------------
    // VEHICLES - POPULAR
    // -----------------------------
    vehicleRepository.save(new Vehicle(
        null, "HB20 1.0", "/public/assets/image/hb20", "XXX-0X00",
        "Branco", true, 1000, true, popular));

    vehicleRepository.save(new Vehicle(
        null, "LOGAN 1.6", "/public/assets/image/logan", "XXX-0X00",
        "Branco", true, 8000, true, popular));

    vehicleRepository.save(new Vehicle(
        null, "Peugeot 2008 1.0", "/public/assets/image/peugeot2008", "XXX-0X00",
        "Branco", true, 6000, true, popular));

    // -----------------------------
    // VEHICLES - FAMÍLIA
    // -----------------------------
    vehicleRepository.save(new Vehicle(
        null, "Chevrolet Spin", "/public/assets/image/spin", "SP0-0X00",
        "Branco", true, 6000, true, familia));

    vehicleRepository.save(new Vehicle(
        null, "Chevrolet Spin", "/public/assets/image/spin", "SPI-0X00",
        "Branco", true, 9000, true, familia));

    vehicleRepository.save(new Vehicle(
        null, "Chevrolet Spin", "/public/assets/image/spin", "SPI-0I00",
        "Branco", true, 1100, true, familia));

    // -----------------------------
    // VEHICLES - PREMIUM
    // -----------------------------
    vehicleRepository.save(new Vehicle(
        null, "Fiat Fastback", "/public/assets/image/fastback", "FIA-0X00",
        "Branco", true, 6000, true, premium));

    vehicleRepository.save(new Vehicle(
        null, "VW Nirus", "/public/assets/image/fastback", "NIV-0X00",
        "Branco", true, 6000, true, premium));

    vehicleRepository.save(new Vehicle(
        null, "GM Onix Plux", "/public/assets/image/fastback", "PLU-0X00",
        "Branco", true, 6000, true, premium));
  }
}
