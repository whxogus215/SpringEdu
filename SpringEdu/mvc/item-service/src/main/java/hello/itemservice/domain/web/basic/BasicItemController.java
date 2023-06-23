package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items"; // 논리적 뷰 이름 반환
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item"; // PathVariable의 경우, 같은 View를 반환하지만, 동적 템플릿이 적용된다. -> 하나의 View를 통해 쉽게 구현이 가능(장점)
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) { // @ModelAttribute는 model.addAttribute까지 자동으로 처리해준다.

        itemRepository.save(item);
        // model.addAttribute("item", item); // 자동으로 추가됨으로 생략 가능
        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { // 속성 값을 생략할 경우, 클래스 이름의 첫 글자를 소문자로 바꿔서 모델 이름으로 자동 설정한다!

        itemRepository.save(item);
        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV4(Item item) { // 객체가 파라미터로 들어올 경우, 어노테이션을 생략하면 @ModelAttribute가 자동으로 추가된다!

        itemRepository.save(item);
        return "basic/item"; // 문제점 : PRG 패턴이 적용되지 않아, 이 상태에서 새로고침할 경우 POST 요청이 또 된다. -> Redirect 필요
    }

    // @PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);
//        return "basic/item";
        // POST 요청이 끝나면 POST의 View를 반환하는 것이 아니라 GET 요청을 받는 다른 URL로 Redirect한다.
        // 이러면 사용자가 다시 새로고침을 해도 최근 요청이 GET이기 때문에 POST 요청의 중복 문제가 발생하지 않는다.
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}"; // PathVariable로 들어가지 못한 값들은 쿼리 파라미터로 자동으로 들어가게 된다.
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
