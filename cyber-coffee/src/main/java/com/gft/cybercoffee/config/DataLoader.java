package com.gft.cybercoffee.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gft.cybercoffee.builder.ReceitaBuilder;
import com.gft.cybercoffee.model.CyberCoffeeUser;
import com.gft.cybercoffee.model.ElementoReceita;
import com.gft.cybercoffee.model.Ingrediente;
import com.gft.cybercoffee.model.Receita;
import com.gft.cybercoffee.model.UnidadeMedida;
import com.gft.cybercoffee.repository.CyberCoffeeUserRepository;
import com.gft.cybercoffee.repository.ElementoReceitaRepository;
import com.gft.cybercoffee.repository.IngredienteRepository;
import com.gft.cybercoffee.repository.ReceitaRepository;
import com.gft.cybercoffee.repository.UnidadeMedidaRepository;
import com.gft.cybercoffee.service.IngredienteService;
import com.gft.cybercoffee.service.ReceitaService;

@Component
public class DataLoader implements CommandLineRunner {

        @Autowired
        private UnidadeMedidaRepository unidadeMedidaRepository;

        @Autowired
        private IngredienteRepository ingredienteRepository;

        @Autowired
        private ReceitaRepository receitaRepository;

        @Autowired
        private ElementoReceitaRepository elementoReceitaRepository;

        @Autowired
        private ReceitaService receitaService;

        @Autowired
        private IngredienteService ingredienteService;
        
        @Autowired
        private CyberCoffeeUserRepository cyberCoffeeUserRepository;

        @Autowired
        private BCryptPasswordEncoder enconder;

        @Override
        public void run(String... args) throws Exception {

                System.out.println("\n\nPOPULANDO BANCO DE DADOS: \n\n");


                CyberCoffeeUser admin = new CyberCoffeeUser();
                admin.setNome("admin");
                admin.setUsername("admin@gft.com");
                admin.setPassword(enconder.encode("Gft@1234"));
                admin.setAuthorities(new SimpleGrantedAuthority("ADMIN"));
                cyberCoffeeUserRepository.save(admin);
                
                CyberCoffeeUser usuario = new CyberCoffeeUser();
                usuario.setNome("user");
                usuario.setUsername("usuario@gft.com");
                usuario.setPassword(enconder.encode("Gft@1234"));
                usuario.setAuthorities(new SimpleGrantedAuthority("USER"));
                cyberCoffeeUserRepository.save(usuario);
                
                List<Ingrediente> ingredientes = new ArrayList<>();
                List<String> nomesIngredientes = Arrays.asList(
                                "Açucar",
                                "Açucar refinado",
                                "Castanhas",
                                "Castanhas picadas",
                                "Pó de café",
                                "Água quente",
                                "Água morna",
                                "Clara(s) em neve",
                                "Clara(s) em neve em ponto firme",
                                "Raspa(s) de chocolate",
                                "Raspa(s) de chocolate para enfeitar",
                                "Essência de café",
                                "Essência de baunilha",
                                "Chocolate meio amargo",
                                "Creme de leite",
                                "Sorvete de creme",
                                "Café bem forte",
                                "Café instantâneo",
                                "Emulsificante",
                                "Chocolate do padre",
                                "Chocolate amargo",
                                "Chocolate amargo 70%",
                                "Achocolatado em pó",
                                "Canela em pó",
                                "Açucar ou adoçante",
                                "Açucar ou adoçante em pó",
                                "Cobertura chocolate",
                                "Creme de leite sem soro",
                                "Creme de leite",
                                "Chocolate em pó",
                                "Essência de baunilha",
                                "Manteiga",
                                "Extrato de café",
                                "Glucose ou xarope de milho",
                                "Manteiga sem sal",
                                "Leite condesado",
                                "Manteiga ou margarina",
                                "Manteiga ou margarina sem sal",
                                "Farinha de trigo",
                                "Fermento em pó",
                                "Ovo(s)",
                                "Manteiga",
                                "Óleo",
                                "Chocolate",
                                "Chocolate em pó",
                                "Água",
                                "Sal",
                                "Cravos",
                                "Confeito(s)",
                                "Café",
                                "Café espresso",
                                "Café espresso intenso",
                                "Leite de coco",
                                "Nutella",
                                "Xarope de amêndoas",
                                "Noz moscada",
                                "Água de coco",
                                "Phisales",
                                "Café solúvel",
                                "Gelo",
                                "Leite em pó instantâneo",
                                "Chantilly",
                                "Bicarbonato de sódio",
                                "Leite");

                nomesIngredientes.forEach(i -> ingredientes.add(new Ingrediente(i)));
                ingredienteRepository.saveAll(ingredientes);

                List<UnidadeMedida> unidadesMedida = new ArrayList<>();
                List<String> nomesUnidadesMedida = Arrays.asList(
                                "A gosto",
                                "Colher(es) de Sopa",
                                "Colher(es) de Chá",
                                "Colher(es) de Café",
                                "Colher(es) de Sobremesa",
                                "Copo(s)",
                                "Xícara(s)",
                                "Lata(s)",
                                "Caixa(s)",
                                "Gota(s)",
                                "g",
                                "ml",
                                "l",
                                "Bola(s)",
                                "Forma(s)",
                                "Cubo(s)",
                                "Copo(s)",
                                "Pitada(s)",
                                "Concha(s)");

                nomesUnidadesMedida.forEach(i -> unidadesMedida.add(new UnidadeMedida(i)));
                unidadeMedidaRepository.saveAll(unidadesMedida);

                List<Receita> receitas = new ArrayList<>();
                Receita receita1 = new ReceitaBuilder()
                                .nome("Café com Leite Condesado e Cravo")
                                .tempo(5)
                                .modo("Coloque o leite para ferver com os cravos. Acrescente o café e ferva um pouco mais. "
                                                + "Coe em uma peneira fina para retirar os cravos e a nata que se forma. Junte o leite condensado, misture bem até dissolver por completo."
                                                + " Despeje em um copo ou xícara e coloque uma colher de chantilly por cima.")
                                .rendimento(1)
                                .imagem("receita1.png")
                                .buildComImagem();

                Receita receita2 = new ReceitaBuilder()
                                .nome("Sorvete de Café com Crocante de Castanhas")
                                .tempo(10)
                                .modo("Você pode colocar primeiro uma colher do café instantâneo. Se achar fraco, coloque mais, ou se achar que já está bom não acrescente a outra colher."
                                                + "Bata tudo no liquidificador (exceto o emulsificante) até misturar bem e leve ao freezer até que congele. DICA: levar pra congelar em uma forma de metal, pois congela mais rápido. "
                                                + "Tire do congelador e bata na batedeira com o emulsificante por 3 a 5 minutos, até que fique fofo e aumente de volume. O emulsificante ajuda a ficar mais leve, mas sem ele também é possível ter um sorvete cremoso.")
                                .rendimento(10)
                                .imagem("receita2.png")
                                .buildComImagem();

                Receita receita3 = new ReceitaBuilder()
                                .nome("Brigadeiro de Café")
                                .tempo(30)
                                .modo("Em uma panela grande junte todos os ingredientes."
                                                + "Coloque no fogo e mexa sem parar. Não se esqueça de sempre mexer as bordas da panela com a espátula."
                                                + "Seu brigadeiro estará pronto quando você virar a panela de lado e a massa do brigadeiro desgrudar totalmente do fundo sem grudar na borda."
                                                + "Coloque a massa em uma travessa grande e espalhe bem. Cubra com plástico filme em contato com a massa."
                                                + "Leve para a geladeira. Quando a massa estiver gelada, faça bolinhas e passe no confeito. Coloque em forminhas e está pronto!!")
                                .rendimento(10)
                                .imagem("receita3.png")
                                .buildComImagem();

                Receita receita4 = new ReceitaBuilder()
                                .nome("Café Gelado com Água de Coco")
                                .tempo(10)
                                .modo("Primeiramente, faça o seu café. Se tiver máquina de espresso, é só 'passar' o café normalmente, como de costume. Caso não tenha, faça um café um pouco mais forte, concentrado, no coador mesmo."
                                                + "Em uma coqueteleira, adicione o café e a água de coco. Depois, acrescente os cubos de gelo e misture até o copo começar a 'suar'. Esse é o momento em que a bebida estará no ponto!"
                                                + "Sirva a seguir. Se preferir, pode adoçar.")
                                .rendimento(1)
                                .imagem("receita4.png")
                                .buildComImagem();

                Receita receita5 = new ReceitaBuilder()
                                .nome("Drink de Espresso Nutella e Noz Moscada")
                                .tempo(5)
                                .modo("Colocar a Nutella no fundo do copo e extrair o Espresso ( ou coloque 40 ml do café quente) com a metade do xarope de amêndoas. Colocar metade do leite de coco e um toque "
                                                + "da noz-moscada. Drink gelado: bater todos os ingredientes (mesma quantidade que o quente) na coqueteleira. Servir com um shoot de água de coco bem gelado e decorado com um phisales.")
                                .rendimento(1)
                                .imagem("receita5.png")
                                .buildComImagem();

                Receita receita6 = new ReceitaBuilder()
                                .nome("Capuccino Caseiro")
                                .tempo(5)
                                .modo("Tampe e chacoalhe bem o pote para misturar tudo. Se você quiser pode bater no liquidificador ou processar para ficar um pó mais fino. "
                                                + "Mas no vídeo eu fiz direto no pote para mostrar que é possível. Para preparar coloque 2 colheres (sopa) na xícara e complete com água bem quente (ou leite).")
                                .rendimento(6)
                                .imagem("receita6.png")
                                .buildComImagem();

                Receita receita7 = new ReceitaBuilder()
                                .nome("Frapê de Café")
                                .tempo(15)
                                .modo("Bata no liquidificador o leite condensado com o café-solúvel, o gelo e 1/2 xícara de água."
                                                + "Coloque as bolas de sorvete em copos altos. Cubra-as com a mistura batida e decore decore com o chantilly e o café-solúvel reservado.")
                                .rendimento(2)
                                .imagem("receita7.png")
                                .buildComImagem();

                Receita receita8 = new ReceitaBuilder()
                                .nome("Café Gelado com Sorvete")
                                .tempo(15)
                                .modo("Bata tudo no liquitificador. Passe calda de chocolate em toda a taça. Despeje o café batido, polvilhe com canela. Pronto.")
                                .rendimento(1)
                                .imagem("receita8.png")
                                .buildComImagem();

                Receita receita9 = new ReceitaBuilder()
                                .nome("Mousse de Café")
                                .tempo(60)
                                .modo("Derreta o chocolate em banho-maria. Depois de derretido, acrescente o café solúvel, já dissolvido em água e as gotas da essência de sua preferência."
                                                + "Em outra vasilha misture bem o creme de leite sem soro com o açúcar. Em seguida jogue esta mistura no chocolate derretido e mexa sem parar, até que fique homogêneo."
                                                + "Acrescente as claras em neve delicadamente, até que estas se incorporem totalmente aos outros ingredientes. Salpique raspas de chocolate se quiser."
                                                + "Deixe na geladeira de 2 a 3 horas.")
                                .rendimento(6)
                                .imagem("receita9.png")
                                .buildComImagem();

                Receita receita10 = new ReceitaBuilder()
                                .nome("Brownie de Café e Chocolate")
                                .tempo(40)
                                .modo("Prepare o café com a quantidade de água quente indicada. Coloque numa panela grande com o açúcar e leve ao fogo para ferver e formar uma calda grossa."
                                                + " Retire 1/2 xícara (chá) de calda ainda quente e junte o chocolate. Coloque a manteiga e os ovos, misturando sempre. Depois adicione a farinha peneirada com "
                                                + "o fermento para obter a massa dos brownies. Leve ao forno para assar por cerca de 20 minutos a 180º graus.")
                                .rendimento(8)
                                .imagem("receita10.png")
                                .buildComImagem();

                receitas.add(receita1);
                receitas.add(receita2);
                receitas.add(receita3);
                receitas.add(receita4);
                receitas.add(receita5);
                receitas.add(receita6);
                receitas.add(receita7);
                receitas.add(receita8);
                receitas.add(receita9);
                receitas.add(receita10);
                receitaRepository.saveAll(receitas);

                List<ElementoReceita> elementosReceita1 = new ArrayList<>();
                elementosReceita1.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(63)));
                elementosReceita1.add(new ElementoReceita(3, unidadesMedida.get(2), ingredientes.get(58)));
                elementosReceita1.add(new ElementoReceita(4, ingredientes.get(47)));
                elementosReceita1.add(new ElementoReceita(3, unidadesMedida.get(1), ingredientes.get(35)));
                elementosReceita1.add(new ElementoReceita(1, ingredientes.get(61)));
                elementosReceita1.forEach(i -> i.setReceita(receita1));
                elementoReceitaRepository.saveAll(elementosReceita1);

                List<ElementoReceita> elementosReceita2 = new ArrayList<>();
                elementosReceita2.add(new ElementoReceita(1, unidadesMedida.get(7), ingredientes.get(35)));
                elementosReceita2.add(new ElementoReceita(2, unidadesMedida.get(8), ingredientes.get(28)));
                elementosReceita2.add(new ElementoReceita(1, unidadesMedida.get(1), ingredientes.get(12)));
                elementosReceita2.add(new ElementoReceita(2, unidadesMedida.get(1), ingredientes.get(17)));
                elementosReceita2.add(new ElementoReceita(1, unidadesMedida.get(3), ingredientes.get(18)));
                elementosReceita2.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(1)));
                elementosReceita2.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(3)));
                elementosReceita2.add(new ElementoReceita(1, unidadesMedida.get(3), ingredientes.get(62)));
                elementosReceita2.forEach(i -> i.setReceita(receita2));
                elementoReceitaRepository.saveAll(elementosReceita2);

                List<ElementoReceita> elementosReceita3 = new ArrayList<>();
                elementosReceita3.add(new ElementoReceita(1, unidadesMedida.get(7), ingredientes.get(35)));
                elementosReceita3.add(new ElementoReceita(175, unidadesMedida.get(10), ingredientes.get(21)));
                elementosReceita3.add(new ElementoReceita(1, unidadesMedida.get(1), ingredientes.get(34)));
                elementosReceita3.add(new ElementoReceita(1, unidadesMedida.get(4), ingredientes.get(33)));
                elementosReceita3.add(new ElementoReceita(1.5, unidadesMedida.get(1), ingredientes.get(32)));
                elementosReceita3.add(new ElementoReceita(1, unidadesMedida.get(17), ingredientes.get(46)));
                elementosReceita3.add(new ElementoReceita(ingredientes.get(48)));
                elementosReceita3.forEach(i -> i.setReceita(receita3));
                elementoReceitaRepository.saveAll(elementosReceita3);

                List<ElementoReceita> elementosReceita4 = new ArrayList<>();
                elementosReceita4.add(new ElementoReceita(40, unidadesMedida.get(11), ingredientes.get(50)));
                elementosReceita4.add(new ElementoReceita(100, unidadesMedida.get(11), ingredientes.get(56)));
                elementosReceita4.add(new ElementoReceita(7, unidadesMedida.get(16), ingredientes.get(59)));
                elementosReceita4.forEach(i -> i.setReceita(receita4));
                elementoReceitaRepository.saveAll(elementosReceita4);

                List<ElementoReceita> elementosReceita5 = new ArrayList<>();
                elementosReceita5.add(new ElementoReceita(80, unidadesMedida.get(11), ingredientes.get(51)));
                elementosReceita5.add(new ElementoReceita(100, unidadesMedida.get(11), ingredientes.get(52)));
                elementosReceita5.add(new ElementoReceita(2, unidadesMedida.get(2), ingredientes.get(53)));
                elementosReceita5.add(new ElementoReceita(60, unidadesMedida.get(11), ingredientes.get(54)));
                elementosReceita5.add(new ElementoReceita(1, unidadesMedida.get(17), ingredientes.get(55)));
                elementosReceita5.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(56)));
                elementosReceita5.add(new ElementoReceita(1, ingredientes.get(57)));
                elementosReceita5.forEach(i -> i.setReceita(receita5));
                elementoReceitaRepository.saveAll(elementosReceita5);

                List<ElementoReceita> elementosReceita6 = new ArrayList<>();
                elementosReceita6.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(60)));
                elementosReceita6.add(new ElementoReceita(3, unidadesMedida.get(1), ingredientes.get(58)));
                elementosReceita6.add(new ElementoReceita(1, unidadesMedida.get(1), ingredientes.get(44)));
                elementosReceita6.add(new ElementoReceita(6, unidadesMedida.get(1), ingredientes.get(25)));
                elementosReceita6.add(new ElementoReceita(1, unidadesMedida.get(3), ingredientes.get(23)));
                elementosReceita6.add(new ElementoReceita(1, unidadesMedida.get(2), ingredientes.get(62)));
                elementosReceita6.forEach(i -> i.setReceita(receita6));
                elementoReceitaRepository.saveAll(elementosReceita6);

                List<ElementoReceita> elementosReceita7 = new ArrayList<>();
                elementosReceita7.add(new ElementoReceita(1, unidadesMedida.get(7), ingredientes.get(35)));
                elementosReceita7.add(new ElementoReceita(2, unidadesMedida.get(1), ingredientes.get(58)));
                elementosReceita7.add(new ElementoReceita(1, unidadesMedida.get(14), ingredientes.get(59)));
                elementosReceita7.add(new ElementoReceita(4, unidadesMedida.get(13), ingredientes.get(15)));
                elementosReceita7.add(new ElementoReceita(0.5, unidadesMedida.get(6), ingredientes.get(45)));
                elementosReceita7.add(new ElementoReceita(unidadesMedida.get(0), ingredientes.get(61)));
                elementosReceita7.forEach(i -> i.setReceita(receita7));
                elementoReceitaRepository.saveAll(elementosReceita7);

                List<ElementoReceita> elementosReceita8 = new ArrayList<>();
                elementosReceita8.add(new ElementoReceita(4, unidadesMedida.get(1), ingredientes.get(15)));
                elementosReceita8.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(16)));
                elementosReceita8.add(new ElementoReceita(4, unidadesMedida.get(1), ingredientes.get(19)));
                elementosReceita8.add(new ElementoReceita(4, unidadesMedida.get(1), ingredientes.get(22)));
                elementosReceita8.add(new ElementoReceita(0.5, unidadesMedida.get(12), ingredientes.get(63)));
                elementosReceita8.add(new ElementoReceita(ingredientes.get(23)));
                elementosReceita8.add(new ElementoReceita(ingredientes.get(24)));
                elementosReceita8.add(new ElementoReceita(ingredientes.get(26)));
                elementosReceita8.forEach(i -> i.setReceita(receita8));
                elementoReceitaRepository.saveAll(elementosReceita8);

                List<ElementoReceita> elementosReceita9 = new ArrayList<>();
                elementosReceita9.add(new ElementoReceita(2, unidadesMedida.get(1), ingredientes.get(58)));
                elementosReceita9.add(new ElementoReceita(6, unidadesMedida.get(1), ingredientes.get(0)));
                elementosReceita9.add(new ElementoReceita(4, unidadesMedida.get(9), ingredientes.get(11)));
                elementosReceita9.add(new ElementoReceita(300, unidadesMedida.get(10), ingredientes.get(13)));
                elementosReceita9.add(new ElementoReceita(1, unidadesMedida.get(7), ingredientes.get(27)));
                elementosReceita9.add(new ElementoReceita(4, ingredientes.get(8)));
                elementosReceita9.add(new ElementoReceita(unidadesMedida.get(0), ingredientes.get(10)));
                elementosReceita9.forEach(i -> i.setReceita(receita9));
                elementoReceitaRepository.saveAll(elementosReceita9);

                List<ElementoReceita> elementosReceita10 = new ArrayList<>();
                elementosReceita10.add(new ElementoReceita(7, unidadesMedida.get(1), ingredientes.get(4)));
                elementosReceita10.add(new ElementoReceita(2, unidadesMedida.get(6), ingredientes.get(5)));
                elementosReceita10.add(new ElementoReceita(1, unidadesMedida.get(6), ingredientes.get(0)));
                elementosReceita10.add(new ElementoReceita(200, unidadesMedida.get(10), ingredientes.get(44)));
                elementosReceita10.add(new ElementoReceita(100, unidadesMedida.get(10), ingredientes.get(37)));
                elementosReceita10.add(new ElementoReceita(3, ingredientes.get(40)));
                elementosReceita10.add(new ElementoReceita(0.5, unidadesMedida.get(6), ingredientes.get(38)));
                elementosReceita10.add(new ElementoReceita(1, unidadesMedida.get(2), ingredientes.get(39)));
                elementosReceita10.forEach(i -> i.setReceita(receita10));
                elementoReceitaRepository.saveAll(elementosReceita10);

                System.out.println("\n\n\t\t\t\t\tBANCO DE DADOS CARREGADO\n\n");
        }

}
