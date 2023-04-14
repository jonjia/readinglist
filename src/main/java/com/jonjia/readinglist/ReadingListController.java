package com.jonjia.readinglist;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/readingList")
public class ReadingListController {

  private ReadingListRepository readingListRepository;
  private AmazonProperties amazonProperties
  ;
  public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties) {
    this.readingListRepository = readingListRepository;
    this.amazonProperties = amazonProperties;
  }

  @GetMapping("/{reader}")
  public String readersBooks(@PathVariable("reader") String reader, Model model) {
    List<Book> readingList = readingListRepository.findByReader(reader);
    if (readingList != null) {
      model.addAttribute("books", readingList);
      model.addAttribute("reader", reader);
      model.addAttribute("amazonID", amazonProperties.getAssociateId());
    }
    return "readingList";
  }

  @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
  public String addToReadingList(@PathVariable("reader") String reader, Book book) {
    book.setReader(reader);
    readingListRepository.save(book);
    return "redirect:/readingList/{reader}";
  }

}
